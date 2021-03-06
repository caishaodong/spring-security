package com.dong.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.dong.springsecurity.model.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author caishaodong
 * @Date 2020-11-01 16:00
 * @Description
 **/
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("100");
        responseBody.setMsg("Logout Success!");

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
