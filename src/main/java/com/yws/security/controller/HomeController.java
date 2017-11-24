package com.yws.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/16 11:48
 * @description
 */
@Controller
public class HomeController {
    @GetMapping("/login")
    public String login(){
        return "/login.html";
    }

    @GetMapping("/loginError")
    public String loginError(){
        return "/error/login_failure.html";
    }

//    @GetMapping("/deny")
//    public String deny(){
//        return "/error/403.html";
//    }

//    @PostMapping("/login.do")
//    public String loginForm(){
//        return "/login.html";
//    }

    @RequestMapping("/index")
    public String goIndex(){
        return "/index.html";
    }
}
