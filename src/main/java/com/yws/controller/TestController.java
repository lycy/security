package com.yws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/15 20:26
 * @name
 * @description
 */
@Controller
public class TestController {
    /**
     * test页面
     * @return
     */
    @RequestMapping("/test")
    public String toHome(){
        return "/admin.html";
    }
    @RequestMapping("/user")
    public String toUser(){
        return "/user.html";
    }
    @RequestMapping("/admin")
    public String toAdmin(){
        return "/admin.html";
    }

    @RequestMapping("/accessDenied")
    public String toAccessDenied(){
        return "/login_failure.jsp";
    }

//    @RequestMapping("/formLogin")
//    public String formLogin(){
//        return "/index.html";
//    }
}
