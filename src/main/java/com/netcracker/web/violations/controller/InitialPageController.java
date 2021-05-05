package com.netcracker.web.violations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialPageController {

    @GetMapping("/")
    public String allViolations(){
        return "initial_page";
    }
}
