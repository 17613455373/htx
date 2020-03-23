package com.example.htx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello1 {

    @RequestMapping("/hello")
    public String Hello(){
        return "huitengxian";
    }

}
