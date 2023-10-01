package com.iflytek.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.iflytek.cloud.speech.ErrorCode;
import com.iflytek.cloud.speech.GrammarListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechEvent;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.util.DebugLog;
import com.iflytek.util.DrawableUtils;
import com.iflytek.util.JsonParser;

public class AsrSpeechView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// 语法文件
	private final static String mGrammar = "#ABNF 1.0 UTF-8;\nlanguage zh-CN;\nmode voice;\nroot $main;\n$main = $place1 到$place2 ;\n$place1 = 北京 | 武汉 | 南京 | 天津 | 天京 | 东京;\n$place2 = 上海 | 合肥;";

	private JButton jbtnUpload;
	private JButton jbtnRecognizer;
	private JButton jbtnHome;
	private JLabel labelWav;
	private JTextArea resultArea;

	private String mGrammarId = null;
	private SpeechRecognizer mAsr = null;

	/**
	 * 初始化按钮. 初始化按钮图片背景、大小、鼠标点击事件
	 */
	public AsrSpeechView() {
		ImageIcon img = new ImageIcon("res/mic_01.png");
		labelWav = new JLabel(img);
		labelWav.setBounds(0, 0, img.getIconWidth(),
				img.getIconHeight() * 4 / 5);

		jbtnRecognizer = addButton("res/button.png", "开始识别", 0, 320, 330, -1,
				"res/button");
		jbtnRecognizer.add(labelWav, BorderLayout.WEST);
		jbtnRecognizer.setEnabled(false);
		jbtnUpload = addButton("res/button.png", "上传", 330, 320, 330, -1,
				"res/button");

		jbtnHome = addButton("res/home.png", "", 20, 20, 1, 1, "res/home");

		resultArea = new JTextArea("");
		resultArea.setBounds(30, 100, 540, 400);
		resultArea.setOpaque(false);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setForeground(Color.BLACK);
		Font font = new Font("宋体", Font.BOLD, 21);
		resultArea.setFont(font);
		resultArea.setText( "请先点击“上传”按钮，上传以下语法。\n");
		resultArea.append( mGrammar );

		setOpaque(false);
		setLayout(null);
		add(jbtnUpload);
		add(jbtnRecognizer);
		add(resultArea);
		add(jbtnHome);

		// 初始化识别对象
		mAsr = SpeechRecognizer.createRecognizer();

		jbtnUpload.addActionListener(this);
		jbtnRecognizer.addActionListener(this);
		jbtnHome.addActionListener(this);
	}

	public JButton addButton(String imgName, String btnName, int x, int y,
			int imgWidth, int imgHeight, String iconPath) {

		JButton btn = null;
		ImageIcon img = new ImageIcon(imgName);
		btn = DrawableUtils.createImageButton(btnName, img, "center");
		int width = imgWidth, height = imgHeight;
		if (width == 1)
			width = img.getIconWidth();
		else if (width == -1)
			width = img.getIconHeight() * 4 / 5;

		if (height == 1)
			height = img.getIconWidth();
		else if (height == -1)
			height = img.getIconHeight() * 4 / 5;

		btn.setBounds(x, y, width, height);

		DrawableUtils.setMouseListener(btn, iconPath);

		return btn;
	}

	/**
	 * 按钮监听器实现
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		resultArea.setText("");
		// 语法识别步骤：1、上传语法成功文件，2、语法识别。
		if (e.getSource() == jbtnUpload) {
			// 指定引擎类型
			mAsr.setParameter(SpeechConstant.ENGINE_TYPE, "cloud");
			mAsr.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
			int ret = mAsr.buildGrammar("abnf", mGrammar, grammarListener);
			if (ret != ErrorCode.SUCCESS)
				DebugLog.Log("语法构建失败,错误码：" + ret);

		} else if (e.getSource() == jbtnRecognizer) {
			if (mGrammarId != null) {
				// 设置云端返回结果为json格式（默认返回josn格式，可设置为xml）
				mAsr.setParameter(SpeechConstant.RESULT_TYPE, "json");
				mAsr.setParameter(SpeechConstant.ASR_AUDIO_PATH, "./asr_test.pcm");
				// 设置云端识别使用的语法id
				mAsr.setParameter(SpeechConstant.CLOUD_GRAMMAR, mGrammarId);
				if (!mAsr.isListening())
					mAsr.startListening(recognizerListener);
				else {
					mAsr.stopListening();
					asrSpeechInitUI();
				}
			}

		} else if (e.getSource() == jbtnHome) {
			resultArea.setText("");
			if (null != mAsr) {
				mAsr.cancel();
				mAsr.destroy();
			}
			JFrame frame = MainView.getFrame();
			frame.getContentPane().remove(this);
			JPanel panel = ((MainView) frame).getMainJpanel();
			frame.getContentPane().add(panel);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		}

	}

	/**
	 * 构建语法监听器
	 */
	private GrammarListener grammarListener = new GrammarListener() {
		@Override
		public void onBuildFinish(String grammarId, SpeechError error) {
			if (error == null) {
				mGrammarId = grammarId;
				resultArea.setText("语法构建成功");
				jbtnRecognizer.setEnabled(true);
				DebugLog.Log("语法构建成功：" + grammarId);
			} else {
				DebugLog.Log("语法构建失败,错误码：" + error.getErrorCode());
				resultArea.setText( "语法构建失败,错误码：" + error.getErrorDescription(true) );
			}
		}
	};

	private RecognizerListener recognizerListener = new RecognizerListener() {

		/**
		 * 获取识别结果. 获取RecognizerResult类型的识别结果，并对结果进行累加，显示到Area里
		 */
		@Override
		public void onResult(RecognizerResult results, boolean islast) {
			// 结果返回为默认json格式,用JsonParser工具类解析
			String text = JsonParser.parseGrammarResult(results
					.getResultString());
			resultArea.append(text);
			
			if( islast ){
				asrSpeechInitUI();
			}

		}

		@Override
		public void onVolumeChanged(int volume) {
			if (volume == 0)
				volume = 1;
			else if (volume >= 6)
				volume = 6;
			labelWav.setIcon(new ImageIcon("res/mic_0" + volume + ".png"));
		}

		@Override
		public void onError(SpeechError error) {
			if (null != error){
				DebugLog.Log("onError Code：" + error.getErrorCode());
				AsrSpeechView.this.resultArea.setText( error.getErrorDescription(true) );
			}
			asrSpeechInitUI();
		}

		@Override
		public void onEvent(int eventType, int arg1, int agr2, String msg) {
			//以下代码用于调试，如果出现问题可以将sid提供给讯飞开发者，用于问题定位排查
			/*if(eventType == SpeechEvent.EVENT_SESSION_ID) {
				DebugLog.Log("sid=="+msg);
			}*/
		}

		@Override
		public void onBeginOfSpeech() {
			((JLabel) jbtnRecognizer.getComponent(0)).setText("请说话...");
		}

		@Override
		public void onEndOfSpeech() {
			((JLabel) jbtnRecognizer.getComponent(0)).setText("等待结果");
		}
	};

	/**
	 * 识别结束，恢复初始状态
	 */
	public void asrSpeechInitUI() {

		labelWav.setIcon(new ImageIcon("res/mic_01.png"));
		((JLabel) jbtnRecognizer.getComponent(0)).setText("开始识别");
	}
}
