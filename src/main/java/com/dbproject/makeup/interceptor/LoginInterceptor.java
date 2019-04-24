package com.dbproject.makeup.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null) { // Not login
            if((request.getRequestURI()).startsWith("/user")) {
                response.sendRedirect("/user");
            }
            else {
                response.sendRedirect("/admin");
            }
            return false;
        }
        return true;
    }
}
