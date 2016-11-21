package com.fxmms.common.security;

/**
 * Created by mark on 16/11/2.
 * @usage  控制用户登录成功之后根据不同的权限登录到不同的欢迎页
 */
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Repository
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        if (roles.contains("admin")){
            response.sendRedirect(basePath+"admin/index");
            return;
        }
        if (roles.contains("superadmin")){
            response.sendRedirect(basePath+"superadmin/index");
            return;
        }
        if (roles.contains("customer")){
            response.sendRedirect(basePath+"customer/index");
            return;
        }
        response.sendRedirect(basePath+"login.jsp");
    }
}