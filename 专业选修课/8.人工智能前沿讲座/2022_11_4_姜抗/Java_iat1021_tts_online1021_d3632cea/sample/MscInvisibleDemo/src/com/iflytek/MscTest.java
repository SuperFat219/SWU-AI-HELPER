package com.iflytek;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.iflytek.cloud.speech.LexiconListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechEvent;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.iflytek.cloud.speech.UserWords;

public class MscTest {

	private static final String APPID = "d3632cea";

	private static final String USER_WORDS = "{\"userword\":[{\"name\":\"计算机词汇\",\"words\":[\"随机存储器\",\"只读存储器\",\"扩充数据输出\",\"局部总线\",\"压缩光盘\",\"十七寸显示器\"]},{\"name\":\"我的词汇\",\"words\":[\"槐花树老街\",\"王小贰\",\"发炎\",\"公事\"]}]}";

	private static MscTest mObject;

	private static StringBuffer mResult = new StringBuffer();
	
	private boolean mIsLoop = true;

	public static void main(String args[]) {
		if( null!=args && args.length>0 && args[0].equals("true") ){
			//在应用发布版本中，请勿显示日志，详情见此函数说明。
			Setting.setShowLog( true );
		}
		
		SpeechUtility.createUtility("appid=" + APPID);
		getMscObj().loop();
	}

	private static MscTest getMscObj() {
		if (mObject == null)
			mObject = new MscTest();
		return mObject;
	}

	private boolean onLoop() {
		boolean isWait = true;
		try {
			DebugLog.Log("*********************************");
			DebugLog.Log("Please input the command");
			DebugLog.Log("1:音频流听写            2：上传词表           3：无声合成           4：退出  ");

			Scanner in = new Scanner(System.in);
			int command = in.nextInt();

			DebugLog.Log("You input " + command);

			switch (command) {
			case 1:
				Recognize();
				break;
			case 2:
				uploadUserWords();
				break;
			case 3:
				Synthesize();
				break;
			case 4:
				mIsLoop = false;
				isWait = false;
				in.close();
				break;
			default:
				isWait = false;
				break;
			}
		} catch (Exception e) {
			
		}
		
		return isWait;
	}

	// *************************************音频流听写*************************************

	/**
	 * 听写
	 */
	
	private boolean mIsEndOfSpeech = false;
	private void Recognize() {
		if (SpeechRecognizer.getRecognizer() == null)
			SpeechRecognizer.createRecognizer();
		mIsEndOfSpeech = false;
		RecognizePcmfileByte();
	}

	/**
	 * 自动化测试注意要点 如果直接从音频文件识别，需要模拟真实的音速，防止音频队列的堵塞
	 */
	public void RecognizePcmfileByte() {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
		//写音频流时，文件是应用层已有的，不必再保存
//		recognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH,
//				"./iat_test.pcm");
		recognizer.setParameter( SpeechConstant.RESULT_TYPE, "plain" );
		recognizer.startListening(recListener);
		
		FileInputStream fis = null;
		final byte[] buffer = new byte[64*1024];
		try {
			fis = new FileInputStream(new File("./test.pcm"));
			if (0 == fis.available()) {
				mResult.append("no audio avaible!");
				recognizer.cancel();
			} else {
				int lenRead = buffer.length;
				while( buffer.length==lenRead && !mIsEndOfSpeech ){
					lenRead = fis.read( buffer );
					recognizer.writeAudio( buffer, 0, lenRead );
				}//end of while
				
				recognizer.stopListening();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fis) {
					fis.close();
					fis = null;
				}
		} catch (IOException e) {
				e.printStackTrace();
			}
		}//end of try-catch-finally
		
	}

	/**
	 * 听写监听器
	 */
	private RecognizerListener recListener = new RecognizerListener() {

		public void onBeginOfSpeech() {
			DebugLog.Log( "onBeginOfSpeech enter" );
			DebugLog.Log("*************开始录音*************");
		}

		public void onEndOfSpeech() {
			DebugLog.Log( "onEndOfSpeech enter" );
			mIsEndOfSpeech = true;
		}

		public void onVolumeChanged(int volume) {
			DebugLog.Log( "onVolumeChanged enter" );
			if (volume > 0)
				DebugLog.Log("*************音量值:" + volume + "*************");

		}

		public void onResult(RecognizerResult result, boolean islast) {
			DebugLog.Log( "onResult enter" );
			mResult.append(result.getResultString());
			
			if( islast ){
				DebugLog.Log("识别结果为:" + mResult.toString());
				mIsEndOfSpeech = true;
				mResult.delete(0, mResult.length());
				waitupLoop();
			}
		}

		public void onError(SpeechError error) {
			mIsEndOfSpeech = true;
			DebugLog.Log("*************" + error.getErrorCode()
					+ "*************");
			waitupLoop();
		}

		public void onEvent(int eventType, int arg1, int agr2, String msg) {
			DebugLog.Log( "onEvent enter" );
		}

	};

	// *************************************无声合成*************************************

	/**
	 * 合成
	 */
	private void Synthesize() {
		SpeechSynthesizer speechSynthesizer = SpeechSynthesizer
				.createSynthesizer();
		// 设置发音人
		speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");

		//启用合成音频流事件，不需要时，不用设置此参数
		speechSynthesizer.setParameter( SpeechConstant.TTS_BUFFER_EVENT, "1" );
		// 设置合成音频保存位置（可自定义保存位置），默认不保存
		speechSynthesizer.synthesizeToUri("语音合成测试程序 ", "./tts_test.pcm",
				synthesizeToUriListener);
	}

	/**
	 * 合成监听器
	 */
	SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {

		public void onBufferProgress(int progress) {
			DebugLog.Log("*************合成进度*************" + progress);

		}

		public void onSynthesizeCompleted(String uri, SpeechError error) {
			if (error == null) {
				DebugLog.Log("*************合成成功*************");
				DebugLog.Log("合成音频生成路径：" + uri);
			} else
				DebugLog.Log("*************" + error.getErrorCode()
						+ "*************");
			waitupLoop();

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
		}

	};

	// *************************************词表上传*************************************

	/**
	 * 词表上传
	 */
	private void uploadUserWords() {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		if ( recognizer == null) {
			recognizer = SpeechRecognizer.createRecognizer();
			
			if( null == recognizer ){
				DebugLog.Log( "获取识别实例实败！" );
				waitupLoop();
				return;
			}
		}

		UserWords userwords = new UserWords(USER_WORDS);
		recognizer.setParameter( SpeechConstant.DATA_TYPE, "userword" );
		recognizer.updateLexicon("userwords",
				userwords.toString(), 
				lexiconListener);
	}

	/**
	 * 词表上传监听器
	 */
	LexiconListener lexiconListener = new LexiconListener() {
		@Override
		public void onLexiconUpdated(String lexiconId, SpeechError error) {
			if (error == null)
				DebugLog.Log("*************上传成功*************");
			else
				DebugLog.Log("*************" + error.getErrorCode()
						+ "*************");
			waitupLoop();
		}

	};

	private void waitupLoop(){
		synchronized(this){
			MscTest.this.notify();
		}
	}

	public void loop() {
		while (mIsLoop) {
			try {
				if (onLoop()) {
					synchronized(this){
						this.wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
