package com.raju.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Home {
    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String welcomePage(){
        return "index";
    }
}
