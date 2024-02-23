package com.ll.sb_2024_02_20_2.domain.home.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Value("${custom.jwt.secretKey}")
    private String jwtSecretKey;
    @GetMapping("/")
    @ResponseBody
    public String showMain() {
        return "홈3";
    }

    @GetMapping("/jwtSecretKey")
    @ResponseBody
    public String showJwtSecretKey() {
        return jwtSecretKey;
    }
}