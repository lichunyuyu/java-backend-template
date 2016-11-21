package com.fxmms.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	protected static final Log log = LogFactory.getLog(WebUtil.class);

	public static HttpServletRequest getHttpServletRequest() {
		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest req = sra.getRequest();
			return req;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public static ServletContext getServletContext() {
		try {
			return getHttpServletRequest().getSession().getServletContext();
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public static String getUserIp() {
		try {
			return getHttpServletRequest().getRemoteAddr();
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

}
