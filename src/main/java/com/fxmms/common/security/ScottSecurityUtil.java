package com.fxmms.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ScottSecurityUtil {
	
	public static String getLoginName(){
		SecurityContext secCtx = SecurityContextHolder.getContext();
		Authentication auth =secCtx.getAuthentication();
		String username = auth.getName();
		return username;
	}

	public static void destorySession() {
		SecurityContext secCtx = SecurityContextHolder.getContext();

	}
}
