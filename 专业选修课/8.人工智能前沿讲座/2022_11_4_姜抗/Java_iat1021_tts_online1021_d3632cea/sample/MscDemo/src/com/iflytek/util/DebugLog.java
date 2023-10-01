package com.iflytek.util;
import java.text.SimpleDateFormat;

public class DebugLog {
	
	private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
	
	public static void Log(String tag,String log)
	{
		if(true)
		    System.out.println(log);
	}
	
	public static void Log(String log)
	{
		String date=sDateFormat.format(new java.util.Date());
		if(true){
		    System.out.println("<" + date + ">" + log);
		}
	}
}
