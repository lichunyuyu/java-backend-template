package com.fxmms.common.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtils {
	protected static final Log log = LogFactory.getLog(CommonUtils.class);

	public static String appendUrlParam(String url, String pname, String pvalue) {
		try {
			if (url.indexOf("?") == -1) {
				url += "?" + pname + "=" + pvalue;
			} else {
				url += "&" + pname + "=" + pvalue;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return url;
	}

	public static String appendUrlParams(String url, Map<String, String> params) {
		if (params == null) {
			return url;
		}
		try {
			for (Map.Entry<String, String> e : params.entrySet()) {
				String key = e.getKey();
				String value = e.getValue();
				url = appendUrlParam(url, key, value);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return url;
	}
}
