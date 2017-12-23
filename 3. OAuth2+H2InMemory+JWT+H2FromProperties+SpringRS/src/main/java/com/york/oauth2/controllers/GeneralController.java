package com.york.oauth2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GeneralController {

    @GetMapping("/")
    public RestMsg hello(){
        return new RestMsg("Hello World!");
    }

    @GetMapping("/api/test")
    public RestMsg apitest(){
        return new RestMsg("Hello apiTest!");
    }

    @GetMapping(value = "/api/hello", produces = "application/json")
    public RestMsg helloUser(){
        // The authenticated user can be fetched using the SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SecurityContext context=SecurityContextHolder.getContext();
        Authentication auth=context.getAuthentication();
   
        System.out.println("hello");
        
        return new RestMsg(String.format("Hello '%s'!", username));
    }

    @GetMapping("/api/admin")
    // If a controller request asks for the Principal user in
    // the method declaration Spring security will provide it.
    public RestMsg helloAdmin(Principal principal){
        return new RestMsg(String.format("Welcome '%s'!", principal.getName()));
    }

    // A helper class to make our controller output look nice
    public static class RestMsg {
        private String msg;
        public RestMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
