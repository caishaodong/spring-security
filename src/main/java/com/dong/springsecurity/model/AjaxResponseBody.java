package com.dong.springsecurity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author caishaodong
 * @Date 2020-11-01 15:57
 * @Description
 **/
@Data
public class AjaxResponseBody implements Serializable {
    private String status;
    private String msg;
    private Object result;
    private String jwtToken;
}
