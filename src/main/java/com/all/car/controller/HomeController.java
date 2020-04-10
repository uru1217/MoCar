package com.all.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
