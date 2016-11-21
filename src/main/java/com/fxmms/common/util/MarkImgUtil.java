package com.fxmms.common.util;

public class MarkImgUtil {
    
	
	/**
	 * @author Mark
	 * @param logourl
	 * @return
	 */
	public static String subStringImgUrl(String logourl){
		String[] logourlFirstarr=	logourl.split(" ");
		String[] logourlSecondarr=logourlFirstarr[1].split("/");
		String realLogo = "";
		for (int i = 2; i < logourlSecondarr.length; i++) {
			realLogo+=logourlSecondarr[i]+"/";
		}
		String[] logurlThird=realLogo.split(".jpg");
		realLogo=logurlThird[0]+".jpg";
		return realLogo;
		
	}
	public static void main(String[] args) {
		String logourl="<img src='/beili/attached/image/20150811/20150811135252_754.jpg' alt='' />";
		System.out.println(subStringImgUrl(logourl));
	}
	
}
