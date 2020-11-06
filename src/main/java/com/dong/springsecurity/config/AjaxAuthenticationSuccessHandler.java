package com.dong.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.dong.springsecurity.model.AjaxResponseBody;
import com.dong.springsecurity.util.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 60*24, "_secret");
        responseBody.setJwtToken(jwtToken);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
