package com.dong.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author caishaodong
 * @Date 2020-11-01 15:59
 * @Description
 **/
@RestController
@RequestMapping("")
public class TestController {

    @GetMapping("/common/index")
    public String index() {
        return "index";
    }

    @GetMapping("/inner")
    public String inner() {
        return "inner";
    }
}
