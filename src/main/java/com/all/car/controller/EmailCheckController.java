package com.all.car.controller;

import com.all.car.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailCheckController {
    private RegService regService;

    @Autowired
    public void setRegService(RegService regService) {
        this.regService = regService;
    }


    @GetMapping("/checkEmail/{email}")
    public ResponseEntity<Integer> checkEmail(@PathVariable String email) {
        System.out.println(email);
        return new ResponseEntity<>(regService.checkEmail(email), HttpStatus.OK);
//        int count = regService.checkEmail(email);
//        System.out.println(count);
//        return new ResponseEntity<>(regService.checkEmail(email), HttpStatus.OK);
    }

}
