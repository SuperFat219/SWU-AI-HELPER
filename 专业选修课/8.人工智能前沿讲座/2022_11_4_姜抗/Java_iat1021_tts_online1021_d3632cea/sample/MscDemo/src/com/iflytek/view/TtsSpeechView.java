package com.iflytek.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.iflytek.cloud.speech.ResourceUtil;
import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechEvent;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.iflytek.util.DebugLog;
import com.iflytek.util.DrawableUtils;

public class TtsSpeechView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// 合成的文本内容
	private String mText = "语音技术实现了人机语音交互，使人与机器之间沟通变得像人与人沟通一样简单。";

	private JButton jbtnPlay;
	private JButton jbtnCancel;
	private JButton jbtnHome;
	private JButton jbtnResume;
	private JButton jbtnPause;
	private JButton jbtnSet;

	private JTextArea resultArea;

	// 语音合成对象
	private SpeechSynthesizer mTts;
	
	//当前要显示的文本
	private String mCurText = "";
	//更新文本的执行对象
	private TextRunnable mTextRunnable = new TextRunnable();
	
	private JPopupMenu mSettingMenu = new JPopupMenu( "设置" );	//主菜单
	
	private Map<String, String[]> mVoiceMap = new LinkedHashMap<String, String[]>();
	private Map<String, String> mParamMap = new HashMap<String, String>();
	
	private String mSavePath = "./tts_test.pcm"; 
	private static final String VAL_TRUE = "1";
	
	private static final String KEY_SHOWLOG = "showlog";
	
	private static class DefaultValue{
		public static final String ENG_TYPE = SpeechConstant.TYPE_CLOUD;
		public static final String VOICE = "小燕";
		public static final String BG_SOUND = "0";
		public static final String SPEED = "50";
		public static final String PITCH = "50";
		public static final String VOLUME = "50";
		public static final String SAVE = "0";
	}

	/**
	 * 初始化按钮. 初始化按钮图片背景、大小、鼠标点击事件
	 */
	public TtsSpeechView() {
		jbtnPlay = addButton("res/btn.png", "合成", 0, 320, 150, "res/btn");
		jbtnPause = addButton("res/btn.png", "暂停", 150, 320, 150, "res/btn");
		jbtnResume = addButton("res/btn.png", "恢复", 300, 320, 150, "res/btn");
		jbtnCancel = addButton("res/btn.png", "取消", 450, 320, 150, "res/btn");
		jbtnHome = addButton("res/home.png", "", 20, 20, -1, "res/home");
		jbtnSet = addButton( "res/setting.png", "", 534, 20, -1, "res/setting" );

		resultArea = new JTextArea("");
		resultArea.setBounds(30, 100, 540, 400);
		resultArea.setOpaque(false);
		resultArea.setEditable(true);
		resultArea.setLineWrap(true);
		resultArea.setForeground(Color.BLACK);
		Font font = new Font("宋体", Font.BOLD, 30);
		resultArea.setFont(font);
		resultArea.setText(mText);

		setOpaque(false);
		setLayout(null);
		add(jbtnPlay);
		add(jbtnCancel);
		add(jbtnResume);
		add(jbtnPause);
		add(resultArea);
		add(jbtnHome);
		add(jbtnSet);

		if (SpeechSynthesizer.getSynthesizer() == null)
			SpeechSynthesizer.createSynthesizer();

		jbtnPlay.addActionListener(this);
		jbtnCancel.addActionListener(this);
		jbtnHome.addActionListener(this);
		jbtnResume.addActionListener(this);
		jbtnPause.addActionListener(this);
		jbtnSet.addActionListener( this );

		// 初始化合成对象
		mTts = SpeechSynthesizer.createSynthesizer();
		
		initParamMap();
		initMenu();
	}

	public JButton addButton(String imgName, String btnName, int x, int y,
			int imgWidth, String iconPath) {

		JButton btn = null;
		ImageIcon img = new ImageIcon(imgName);
		btn = DrawableUtils.createImageButton(btnName, img, "center");
		int width = imgWidth, height = img.getIconHeight();
		if (width == -1)
			width = img.getIconWidth();
		else
			height = img.getIconHeight() * 3 / 4;
		btn.setBounds(x, y, width, height);
		DrawableUtils.setMouseListener(btn, iconPath);

		return btn;
	}

	/**
	 * 按钮监听器实现
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		DebugLog.Log( "actionPerformed enter" );
		
		if (e.getSource() == jbtnPlay) {
			setting();
			// 合成文本为TEXT_CONTENT的句子，设置监听器为mSynListener
			mText = resultArea.getText().trim();
			mTts.startSpeaking( mText, mSynListener );
		} else if (e.getSource() == jbtnCancel) {
			mTts.stopSpeaking();
		} else if (e.getSource() == jbtnPause) {
			DebugLog.Log( "click pause." );
			mTts.pauseSpeaking();
		} else if (e.getSource() == jbtnResume) {
			mTts.resumeSpeaking();
		} else if (e.getSource() == jbtnHome) {
			if (null != mTts) {
				mTts.stopSpeaking();
				mTts.destroy();
			}

			JFrame frame = MainView.getFrame();
			frame.getContentPane().remove(this);
			JPanel panel = ((MainView) frame).getMainJpanel();
			frame.getContentPane().add(panel);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		}else if( jbtnSet.equals(e.getSource()) ){
			DebugLog.Log( "actionPerformed setting" );
			mSettingMenu.show( this, this.jbtnSet.getX(), this.jbtnSet.getY()+50 );
		}
		
		DebugLog.Log( "actionPerformed leave" );
	}

	private SynthesizerListener mSynListener = new SynthesizerListener() {

		@Override
		public void onSpeakBegin() {
		}

		@Override
		public void onBufferProgress(int progress, int beginPos, int endPos,
				String info) {
			DebugLog.Log("--onBufferProgress--progress:" + progress
					+ ",beginPos:" + beginPos + ",endPos:" + endPos);
		}

		@Override
		public void onSpeakPaused() {

		}

		@Override
		public void onSpeakResumed() {

		}

		@Override
		public void onSpeakProgress(int progress, int beginPos, int endPos) {
			DebugLog.Log("onSpeakProgress enter progress:" + progress
					+ ",beginPos:" + beginPos + ",endPos:" + endPos);

			updateText( mText.substring( beginPos, endPos+1 ) );
			
			DebugLog.Log( "onSpeakProgress leave" );
		}

		@Override
		public void onCompleted(SpeechError error) {
			DebugLog.Log( "onCompleted enter" );
			
			String text = mText;
			if (null != error){
				DebugLog.Log("onCompleted Code：" + error.getErrorCode());
				text = error.getErrorDescription(true);
			}
			
			updateText( text );
			
			DebugLog.Log( "onCompleted leave" );
		}


		@Override
		public void onEvent(int eventType, int arg1, int arg2, int arg3, Object obj1, Object obj2) {
			if( SpeechEvent.EVENT_TTS_BUFFER == eventType ){
				DebugLog.Log( "onEvent: type="+eventType
						+", arg1="+arg1
						+", arg2="+arg2
						+", arg3="+arg3
						+", obj2="+(String)obj2 );
				ArrayList<?> bufs = null;
				if( obj1 instanceof ArrayList<?> ){
					bufs = (ArrayList<?>) obj1;
				}else{
					DebugLog.Log( "onEvent error obj1 is not ArrayList !" );
				}//end of if-else instance of ArrayList
				
				if( null != bufs ){
					for( final Object obj : bufs ){
						if( obj instanceof byte[] ){
							final byte[] buf = (byte[]) obj;
							DebugLog.Log( "onEvent buf length: "+buf.length );
						}else{
							DebugLog.Log( "onEvent error element is not byte[] !" );
						}
					}//end of for
				}//end of if bufs not null
			}//end of if tts buffer event
			//以下代码用于调试，如果出现问题可以将sid提供给讯飞开发者，用于问题定位排查
			/*else if(SpeechEvent.EVENT_SESSION_ID == eventType) {
				DebugLog.Log("sid=="+(String)obj2);
			}*/
		}
	};

	private class TextRunnable implements Runnable{
		@Override
		public void run() {
			resultArea.setText( mCurText );
		}//end of function run
		
	}//end of class TextRunnable
	
	private void updateText( final String text ){
		this.mCurText = text;
		SwingUtilities.invokeLater( mTextRunnable );
	}

	private void initMenu(){
		//显示日志
		{
			Map<String, String> logMap = new LinkedHashMap<String, String>();
			logMap.put( Boolean.toString(true), "打开" );
			logMap.put( Boolean.toString(false), "关闭" );
			
			addRadioMenu( "打印日志", KEY_SHOWLOG, logMap, Boolean.toString(Setting.getShowLog()), mRadioItemListener );
		}//end of menuEng
		
		//引擎类型选择
		{
			Map<String, String> engMap = new LinkedHashMap<String, String>();
			engMap.put( SpeechConstant.TYPE_CLOUD, "云端" );
			engMap.put( SpeechConstant.TYPE_LOCAL, "本地" );
			
			JMenu engMenu = addRadioMenu( "引擎类型", SpeechConstant.ENGINE_TYPE, engMap, DefaultValue.ENG_TYPE, mRadioItemListener );
			engMenu.setEnabled( false );	//目前暂不支持离线模式；
		}//end of menuEng
		
		//发音人选择
		{
			this.initVoiceMap();
			Map<String, String> voiceItemMap = new LinkedHashMap<String, String>();
			for( Entry<String, String[]> entry : this.mVoiceMap.entrySet() ){
				voiceItemMap.put( entry.getKey(), entry.getKey() );
			}
			addRadioMenu( "发音人", SpeechConstant.VOICE_NAME, voiceItemMap, DefaultValue.VOICE, mRadioItemListener );
		}//end of menuVoice
		
		//背景音乐
		{
			Map<String, String> bgMap = new LinkedHashMap<String, String>();
			bgMap.put( "1", "开" );
			bgMap.put( "0", "关" );
			
			addRadioMenu( "背景音乐", SpeechConstant.BACKGROUND_SOUND, bgMap, DefaultValue.BG_SOUND, mRadioItemListener );
		}//end of menuBackGround
		
		//语速、语调、音量
		{
			Map<String, String> percentMap = new LinkedHashMap<String, String>();
			percentMap.put( SpeechConstant.SPEED, "语速" );
			percentMap.put( SpeechConstant.PITCH, "语调" );
			percentMap.put( SpeechConstant.VOLUME, "音量" );
			
			for( Entry<String, String> entry: percentMap.entrySet() ){
				this.addSliderMenu( entry.getValue()
						, entry.getKey()
						, 0
						, 100
						, Integer.valueOf(DefaultValue.SPEED)
						, this.mChangeListener );
			}//end of for percentMap
			
		}//end of 语速，语调，音量
		
		//保存音频文件
		{
			Map<String, String> saveMap = new LinkedHashMap<String ,String>();
			saveMap.put( "1", "开" );
			saveMap.put( "0", "关" );
			
			this.addRadioMenu( "保存音频", SpeechConstant.TTS_AUDIO_PATH, saveMap, DefaultValue.SAVE, this.mRadioItemListener );
		}
		
	}//end of function initMenu
	
	private JMenu addRadioMenu( final String text, final String name, Map<String, String> cmd2Names, final String defaultVal, ActionListener actionListener ){
		JMenu menu = new JMenu( text );
		menu.setName( name );
		ButtonGroup group = new ButtonGroup();
		
		for( Entry<String, String>entry : cmd2Names.entrySet() ){
			JRadioButtonMenuItem item = new JRadioButtonMenuItem( entry.getValue(), false );
			item.setName( name );
			item.setActionCommand( entry.getKey() );
			item.addActionListener( actionListener );
			if( defaultVal.equals(entry.getKey()) ){
				item.setSelected( true );
			}
			
			group.add( item );
			menu.add( item );
		}
		
		this.mSettingMenu.add( menu );
		
		return menu;
	}
	
	private void addSliderMenu( final String text, final String name, final int min, final int max, final int defaultVal, ChangeListener changeListener ){
		JMenu menu = new JMenu( text );
		
		JSlider slider = new JSlider( SwingConstants.HORIZONTAL
				, min
				, max
				, defaultVal );
		
		slider.addChangeListener( this.mChangeListener );
		slider.setName( name );
		slider.setPaintTicks( true );
		slider.setPaintLabels( true );
		final int majarTick = Math.max( 1, (max-min)/5 );
		slider.setMajorTickSpacing( majarTick );
		slider.setMinorTickSpacing( majarTick/2 );
		menu.add( slider );
		
		this.mSettingMenu.add( menu );
	}
	
	//选择监听器
	private ActionListener mRadioItemListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			DebugLog.Log( "mRadioItemListener actionPerformed etner action command="+event.getActionCommand() );
			Object obj = event.getSource();
			if( obj instanceof JMenuItem ){
				JMenuItem item = (JMenuItem)obj;
				DebugLog.Log( "mRadioItemListener actionPerformed name="+item.getName()+", value="+event.getActionCommand() );
				String value = event.getActionCommand();
				if( SpeechConstant.TTS_AUDIO_PATH.equals(item.getName()) ){
					value = VAL_TRUE.equalsIgnoreCase(value) ? mSavePath : null;
				}
				
				if( KEY_SHOWLOG.equals(item.getName()) ){
					Setting.setShowLog( Boolean.parseBoolean(value) );
				}else{
					mParamMap.put( item.getName(), value );
				}
				
			}else{
				DebugLog.Log( "mRadioItemListener actionPerformed source object is not JMenuItem" );
			}// end of if-else is object instance of JMenuItem
			
		}
		
	}; //end of  mEngItemListener
	
	//滑动条监听器
	private ChangeListener mChangeListener = new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent event) {
			DebugLog.Log( "mChangeListener stateChanged enter" );
			Object obj = event.getSource();
			if( obj instanceof JSlider ){
				JSlider slider = (JSlider)obj;
				DebugLog.Log( "bar name="+slider.getName()+", value="+slider.getValue() );

				mParamMap.put( slider.getName(), String.valueOf(slider.getValue()) );
			}else{
				DebugLog.Log( "mChangeListener stateChanged source object is not JProgressBar" );
			}
		}
		
	};
	
	//初始化发音人镜像表，云端对应的本地
	//为了查找本地资源方便，请把资源文件置为发音人参数+.jet，如“xiaoyan.jet”
	void initVoiceMap(){
		mVoiceMap.clear();
		String[] names = null;
		
		names = new String[2];
		names[0] = names[1] = "xiaoyan";
		this.mVoiceMap.put( "小燕", names );	//小燕
		
		names = new String[2];
		names[0] = names[1] = "xiaoyu";
		this.mVoiceMap.put( "小宇", names );	//小宇
		
		names = new String[2];
		names[0] = "vixf"; names[1] = "xiaofeng";
		this.mVoiceMap.put( "小峰", names );	//小峰
		
		names = new String[2];
		names[0] = "vixm"; names[1] = "xiaomei";
		this.mVoiceMap.put( "小梅", names );	//小梅
		
		names = new String[2];
		names[0] = "vixr"; names[1] = "xiaorong";
		this.mVoiceMap.put( "小蓉", names );	//小蓉
		
		names = new String[2];
		names[0] = names[1] = "catherine";
		this.mVoiceMap.put( "凯瑟琳", names );	//凯瑟琳
	}
	
	void setting(){
		final String engType = this.mParamMap.get(SpeechConstant.ENGINE_TYPE);
		String voiceName = null; 
		
		for( Entry<String, String> entry : this.mParamMap.entrySet() ){
			String value = entry.getValue();
			if( SpeechConstant.VOICE_NAME.equals(entry.getKey()) ){
				String[] names = this.mVoiceMap.get( entry.getValue() );
				voiceName = value = SpeechConstant.TYPE_CLOUD.equals(engType) ? names[0] : names[1]; 
			}
			
			mTts.setParameter( entry.getKey(), value );
		}
		
		//本地合成时设置资源，并启动引擎
		if( SpeechConstant.TYPE_LOCAL.equals(engType) ){
			//启动合成引擎
			mTts.setParameter( ResourceUtil.ENGINE_START, SpeechConstant.ENG_TTS );
			//设置资源路径
			String curPath = System.getProperty("user.dir");
			DebugLog.Log( "Current path="+curPath );
			String resPath = ResourceUtil.generateResourcePath( curPath+"/tts/common.jet" )
					+ ";" + ResourceUtil.generateResourcePath( curPath+"/tts/"+voiceName+".jet" );
			System.out.println( "resPath="+resPath );
			mTts.setParameter( ResourceUtil.TTS_RES_PATH, resPath );
		}// end of if is TYPE_LOCAL
		
		//启用合成音频流事件，不需要时，不用设置此参数
		mTts.setParameter( SpeechConstant.TTS_BUFFER_EVENT, "1" );
	}// end of function setting
	
	private void initParamMap(){
		this.mParamMap.put( SpeechConstant.ENGINE_TYPE, DefaultValue.ENG_TYPE );
		this.mParamMap.put( SpeechConstant.VOICE_NAME, DefaultValue.VOICE );
		this.mParamMap.put( SpeechConstant.BACKGROUND_SOUND, DefaultValue.BG_SOUND );
		this.mParamMap.put( SpeechConstant.SPEED, DefaultValue.SPEED );
		this.mParamMap.put( SpeechConstant.PITCH, DefaultValue.PITCH );
		this.mParamMap.put( SpeechConstant.VOLUME, DefaultValue.VOLUME );
		this.mParamMap.put( SpeechConstant.TTS_AUDIO_PATH, null );
	}
}
