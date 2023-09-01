package com.josuejs23.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String sayHello(){
        return "home";
    }

    @GetMapping("/leaders/**")
    public String leadersPage(){ return "leaders"; }

    @GetMapping("/system/**")
    public String systemPage(){ return "system"; }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
