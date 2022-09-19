package com.zhuke.corsdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsController {
    @RequestMapping("hello")
    public String helloWord(){
        return "hello";
    }
}
