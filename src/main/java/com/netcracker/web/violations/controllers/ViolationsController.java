package com.netcracker.web.violations.controllers;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
    @RequestMapping("/violations")
    public class ViolationsController {
        private final ViolationsDAOImpl violationDAO;
        private final FineDAOImpl fineDAO;

        @Autowired
        public ViolationsController(ViolationsDAOImpl violationDAO, FineDAOImpl fineDAO) {
            this.violationDAO = violationDAO;
            this.fineDAO = fineDAO;
        }


       @GetMapping()
        public ModelAndView allViolations() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("violations/violations_page");
            ArrayList<ViolationOutput> violations = new ArrayList<>();
            for(Violation violation : violationDAO.allViolations()){
                ViolationOutput violationAdd = violationDAO.convertToOutput(violation);
                violations.add(violationAdd);
            }
            modelAndView.addObject("violations", violations);
            return modelAndView;
        }

        @GetMapping("/{id}/violations")
        public ModelAndView showViolation(@PathVariable("id") int id) {
            ModelAndView modelAndView = new ModelAndView();
            return modelAndView;
        }

        /*@GetMapping("/new")
        public ModelAndView newViolation(@ModelAttribute("violation") Violation violation) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("violations/new");
            return modelAndView;
        }*/

        //TODO: не уверен в url, место возможной ошибки
        @PostMapping()
        public ModelAndView create(@ModelAttribute("violation") Violation violation, BindingResult bindingResult) {
            ModelAndView modelAndView = new ModelAndView();
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("violations/new");
            }
            violationDAO.save(violation);
            modelAndView.setViewName("redirect:/violations");
            return modelAndView;
        }

        @GetMapping("/{id}/edit")
        public ModelAndView edit(@PathVariable("id") int id) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("violations/edit");
            ViolationOutput violationOutput = violationDAO.convertToOutput(violationDAO.get(id));
            modelAndView.addObject("violationOutput", violationOutput);
            modelAndView.addObject("fines", fineDAO.allFines());
            return modelAndView;
        }

    @PostMapping("/{id}")
    public ModelAndView update(@ModelAttribute("violationOutput") ViolationOutput violationUpdated, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        violationDAO.update(id, violationUpdated);
        modelAndView.setViewName("redirect:/violations");
        return modelAndView;
    }
}
