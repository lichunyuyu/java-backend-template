package com.fxmms.common.util;

public class ChangeRwType {
    private static String TYPE_ARTICLE="软文";
    private static String TYPE_PICTURE="美图";
    private static String TYPE_VIDEO="视频";

    public static String changeRwType(String type){
    	
    	if("meiwen".equals(type))
    		return  TYPE_ARTICLE;
    	if("meitu".equals(type))
    		return TYPE_PICTURE;
    	else
    		return TYPE_VIDEO;
    	
    }
}
