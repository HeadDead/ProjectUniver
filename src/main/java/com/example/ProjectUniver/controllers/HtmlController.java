package com.example.ProjectUniver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/confirm-account")
    public String confirmAccount(){return "confirmAccount";}

    @GetMapping("/auth/login")
    public String login(){return "login";}

    @GetMapping("/auth/registrationuser")
    public String registrationUser(){return "registrationUser";}

    @GetMapping("/auth/registration")
    public String registrationOrganization(){return "registrationOrganization";}

    @GetMapping("/index")
    public String index(){return "mainPage";}

    @GetMapping("/createapplication")
    public String createApplication(){return "application";}

    @GetMapping("/submitapplication")
    public String confirm(){return "userApplicationPage";}

    /*@GetMapping("/hui")
    public String*/
}
