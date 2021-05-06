package com.netcracker.web.violations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarsPageController {

    @GetMapping("/cars")
    public ModelAndView allCars(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars_page");
        return modelAndView;
    }
}
