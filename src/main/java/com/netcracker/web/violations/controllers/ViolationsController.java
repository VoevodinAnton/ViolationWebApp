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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/violations")
public class ViolationsController {
    private final CarDAOImpl carDAO;
    private final ViolationsDAOImpl violationDAO;
    private final FineDAOImpl fineDAO;


    @Autowired
    public ViolationsController(CarDAOImpl carDAO, ViolationsDAOImpl violationDAO, FineDAOImpl fineDAO) {
        this.carDAO = carDAO;
        this.violationDAO = violationDAO;
        this.fineDAO = fineDAO;
    }


    @GetMapping()
    public ModelAndView allViolations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("violations/violations_page");
        ArrayList<ViolationOutput> violations = new ArrayList<>();
        for (Violation violation : violationDAO.allViolations()) {
            ViolationOutput violationAdd = violationDAO.convertToOutput(violation);
            violations.add(violationAdd);
        }
        modelAndView.addObject("violations", violations);
        modelAndView.addObject("cars", carDAO.allCars());
        modelAndView.addObject("fines", fineDAO.allFines());
        String[] searchParameters = new String[4];
        modelAndView.addObject("searchParameters", searchParameters);
        return modelAndView;
    }



    @PostMapping()
    public ModelAndView create(@ModelAttribute("violation") @Valid Violation violation, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("violations/new");
            return modelAndView;
        }
        violationDAO.save(violation);
        modelAndView.setViewName("redirect:/violations");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("violations/edit");
        modelAndView.addObject("cars", carDAO.allCars());
        modelAndView.addObject("violation", violationDAO.get(id));
        modelAndView.addObject("fines", fineDAO.allFines());
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@ModelAttribute("violation") @Valid Violation violationUpdated, BindingResult bindingResult, @PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        //TODO: заглушка, убрать когда будет починен чекбокс
        violationUpdated.setStatus(1);
        if (bindingResult.hasErrors()){
            modelAndView.addObject("cars", carDAO.allCars());
            modelAndView.addObject("violation", violationDAO.get(id));
            modelAndView.addObject("fines", fineDAO.allFines());
            modelAndView.setViewName("violations/edit");
            return modelAndView;
        }
        violationDAO.update(id, violationUpdated);
        modelAndView.setViewName("redirect:/violations");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteViolation(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        violationDAO.delete(id);
        modelAndView.setViewName("redirect:/violations");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam Map<String,String> requestParams){
        ModelAndView modelAndView = new ModelAndView();

        List<Violation> violationList = violationDAO.searchViolation(requestParams.get("number"),requestParams.get("type"), requestParams.get("status"));
        modelAndView.addObject("violations", violationList);

        modelAndView.setViewName("violations/search_page");

        return modelAndView;
    }






}
