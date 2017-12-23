package com.york.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeneralController {
    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
    
    @RequestMapping("/api/user")
    public String apiUser() {
        return "Hello /api/user";
    }   
    
    @RequestMapping("/api/admin")
    public String apiAdmin() {
        return "Hello /api/admin";
    }     
}