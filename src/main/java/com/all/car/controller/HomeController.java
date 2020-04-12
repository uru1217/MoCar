package com.all.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/index";
    }


    @GetMapping("/memberlogin")
    public String memberlogin() {
        return "/memberlogin";
    }

    @GetMapping("/memberReg")
    public String memberReg() {
        return "/memberReg";
    }

    @RequestMapping("/naverlogin")
    public String naverlogin(HttpSession session) {
        return "/naverlogin";
    }

    @RequestMapping("/callback")
    public String callback(HttpServletRequest request) throws Exception {
        return "/callback";
    }


}
