package com.all.car.controller;

import com.all.car.model.UserModel;
import com.all.car.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegController {
    private RegService regService;

    @Autowired
    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    @PostMapping("/register")
    public String insert(UserModel userModel) {
        System.out.println(userModel);
       regService.insert(userModel);
        return "redirect:/index";
    }

}
