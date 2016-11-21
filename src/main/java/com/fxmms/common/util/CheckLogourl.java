package com.fxmms.common.util;

public class CheckLogourl {

	
	public static String VIDEO = "embed";

	

	public static String checkLogourl(String logourl) {
		if (logourl.contains(VIDEO)) {

			logourl = logourl.substring(21, 67);
			return logourl;

		} else {
			logourl = logourl.substring(19, 65);
			return logourl;
		}

	}
}
