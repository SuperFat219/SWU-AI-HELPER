package com.iflytek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.util.DrawableUtils;
import com.iflytek.util.Version;

/**
 * MscDemo It's a Sample using MSC SDK, include tts, isr. you can just press
 * button to use it.
 * 
 * @author cyhu 2012-06-14
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JPanel mMainJpanel;
	private JPanel mContentPanel;
	private static JFrame mJframe;

	private JButton jbtnRecognize;
	private JButton jbtnGrammar;
	private JButton jbtnSynthesize;

	/**
	 * 界面初始化.
	 * 
	 */
	public MainView() {
		// 初始化
		StringBuffer param = new StringBuffer();
		param.append( "appid=" + Version.getAppid() );
//		param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
		SpeechUtility.createUtility( param.toString() );
		Setting.setShowLog(false);
		// 设置界面大小，背景图片
		ImageIcon background = new ImageIcon("res/index_bg.png");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		int frameWidth = background.getIconWidth();
		int frameHeight = background.getIconHeight();

		setSize(frameWidth, frameHeight);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon imgRecognize = new ImageIcon("res/btn_recognize.png");
		jbtnRecognize = this.createImageButton(imgRecognize);
		jbtnRecognize.setBounds(10, 150, imgRecognize.getIconWidth(),
				imgRecognize.getIconHeight());
		DrawableUtils.setMouseListener(jbtnRecognize, "res/btn_recognize");

		ImageIcon imgGrammar = new ImageIcon("res/btn_grammar.png");
		jbtnGrammar = this.createImageButton(imgGrammar);
		jbtnGrammar.setBounds(160, 150, imgGrammar.getIconWidth(),
				imgGrammar.getIconHeight());
		DrawableUtils.setMouseListener(jbtnGrammar, "res/btn_grammar");

		//ImageIcon imgUnderstander = new ImageIcon("res/btn_understander.png");

		ImageIcon imgSynthesize = new ImageIcon("res/btn_synthesize.png");
		jbtnSynthesize = this.createImageButton(imgSynthesize);
		jbtnSynthesize.setBounds(460, 150, imgSynthesize.getIconWidth(),
				imgSynthesize.getIconHeight());
		DrawableUtils.setMouseListener(jbtnSynthesize, "res/btn_synthesize");

		GridLayout gridlayout = new GridLayout(0, 3);
		gridlayout.setHgap(10);
		mMainJpanel = new JPanel(gridlayout);
		mMainJpanel.setOpaque(false);

		mMainJpanel.add(jbtnRecognize);
		mMainJpanel.add(jbtnGrammar);
		mMainJpanel.add(jbtnSynthesize);

		jbtnRecognize.addActionListener(this);
		jbtnGrammar.addActionListener(this);
		jbtnSynthesize.addActionListener(this);

		mContentPanel = new JPanel(new BorderLayout());
		mContentPanel.setOpaque(false);
		mContentPanel.add(mMainJpanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setContentPane(mContentPanel);
		setVisible(true);
	}

	/**
	 * Demo入口函数.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		mJframe = new MainView();
	}

	public static JFrame getFrame() {
		return mJframe;
	}

	public JButton createImageButton(ImageIcon img) {
		JButton button = new JButton("");
		button.setIcon(img);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBackground(null);

		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnRecognize) {
			mContentPanel.remove(mMainJpanel);
			mContentPanel.add(new IatSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		} else if (e.getSource() == jbtnGrammar) {
			mContentPanel.remove(mMainJpanel);
			mContentPanel.add(new AsrSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		}  else if (e.getSource() == jbtnSynthesize) {
			mContentPanel.remove(mMainJpanel);
			mContentPanel.add(new TtsSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		}
	}

	public JPanel getMainJpanel() {
		return mMainJpanel;
	}

	public JPanel getContePanel() {
		return mContentPanel;
	}
}