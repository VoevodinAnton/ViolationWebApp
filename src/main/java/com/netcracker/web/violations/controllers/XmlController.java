package com.netcracker.web.violations.controllers;

import com.netcracker.web.violations.services.XmlIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/xml")
public class XmlController {

    @Autowired
    private XmlIO xmlIO;

    @GetMapping()
    public ModelAndView getAllDatabase(){
        ModelAndView modelAndView = new ModelAndView();
        xmlIO.importFromFile("src/main/webapp/res/xml-database/database.xml");
        modelAndView.setViewName("");
        return modelAndView;
    }
}
