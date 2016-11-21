package com.fxmms.common.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mark on 16/11/2.
 * @usage 设置403
 */
@Repository
public class AccessDeniedServletHandler implements AccessDeniedHandler {
    private static final String DEF_ERROR_PAGE_PATH="/403.jsp";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect(DEF_ERROR_PAGE_PATH);
    }
}
