package com.objcat.servicea.intercept.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;


@Component
public class InterceptConfig extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取到请求的URI
        String contentPath = request.getContextPath();
        String uri = request.getRequestURI().toString();
        if(uri.indexOf("test") >0){
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("被拦截了!!!");
            return false;
        }else {
            return true;
        }
    }
}
