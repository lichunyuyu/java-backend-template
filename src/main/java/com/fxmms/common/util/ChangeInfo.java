package com.fxmms.common.util;

public class ChangeInfo {

	public static String SUCCESS ="添加成功";
	public static String DELETE ="删除成功";
	
	
	
	public static String changeInfo(String info){
		
		if("suc".equals(info))
		  return  SUCCESS;
		else
			return DELETE;
		
	}
	
}
