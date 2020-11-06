package com.dong.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.dong.springsecurity.model.AjaxResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author caishaodong
 * @Date 2020-11-01 15:59
 * @Description 它在用户请求处理过程中遇到认证异常时，被ExceptionTranslationFilter用于开启特定认证方案(authentication schema)的认证流程。
 * 这里参数request是遇到了认证异常authException用户请求，response是将要返回给客户的相应，方法commence实现,也就是相应的认证方案逻辑会修改response并返回给用户引导用户进入认证流程。
 **/
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("000");
        responseBody.setMsg("Need Authorities!");

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
