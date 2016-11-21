package com.fxmms.common.util;

public class GetShowPage {

	
	 private static String TO_INDEXPAGE="index";
	 private static String TO_BINDPAGE="bindpage";
	 
	public static String getShowPage(String msg) {
		if ("进入首页".equals(msg)) {
			return  TO_INDEXPAGE;
		}
		
		return TO_BINDPAGE;
	}
	
	
	
}
