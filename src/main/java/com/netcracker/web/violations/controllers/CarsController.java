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

@Controller
@RequestMapping("/cars")
public class CarsController {
    private final CarDAOImpl carDAO;
    private final ViolationsDAOImpl violationsDAO;
    private final FineDAOImpl fineDAO;

    @Autowired
    public CarsController(CarDAOImpl carDAO, ViolationsDAOImpl violationsDAO, FineDAOImpl fineDAO) {
        this.carDAO = carDAO;
        this.violationsDAO = violationsDAO;
        this.fineDAO = fineDAO;
    }


    @GetMapping()
    public ModelAndView allCars() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/cars_page");
        modelAndView.addObject("cars", carDAO.getAllCars());
        return modelAndView;
    }


    @GetMapping("/new")
    public ModelAndView newCar(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/new");
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView create(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cars/new");
            return modelAndView;
        }
        carDAO.save(car);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }


    @GetMapping("/{id}/violations")
    public ModelAndView showCarViolations(@PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/car_violations");
        ArrayList<ViolationOutput> violations = new ArrayList<>();
        for (Violation violation : violationsDAO.showViolations(idCar)) {
            ViolationOutput violationAdd = violationsDAO.convertToOutput(violation);
            violations.add(violationAdd);
        }
        modelAndView.addObject("carViolations", violations);
        modelAndView.addObject("car", carDAO.get(idCar));
        return modelAndView;
    }

    @GetMapping("/{id}/violations/new")
    public ModelAndView newViolation(@ModelAttribute("violation") Violation violation, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("violations/new");
        modelAndView.addObject("car", carDAO.get(idCar));
        modelAndView.addObject("fines", fineDAO.getAllFines());
        return modelAndView;
    }

    @PostMapping("/{id}/violations/new")
    public ModelAndView createViolation(@ModelAttribute("violation") @Valid Violation violation, BindingResult bindingResult, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        violation.setId_car(idCar);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("car", carDAO.get(idCar));
            modelAndView.addObject("fines", fineDAO.getAllFines());
            modelAndView.setViewName("violations/new");
            return modelAndView;
        }
        violationsDAO.save(violation);
        modelAndView.setViewName("redirect:/cars/" + idCar + "/violations");
        return modelAndView;
    }

    @GetMapping("/{id}/violations/{idViolation}/edit")
    public ModelAndView editViolation(@PathVariable("idViolation") int idViolation, @PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/editViolation");
        modelAndView.addObject("violation", violationsDAO.get(idViolation));
        modelAndView.addObject("car", carDAO.get(id));
        modelAndView.addObject("fines", fineDAO.getAllFines());
        return modelAndView;
    }

    @PatchMapping("/{id}/violations/{idViolation}")
    public ModelAndView updateViolation(@ModelAttribute("violation") @Valid Violation violationUpdated, BindingResult bindingResult, @PathVariable("idViolation") int idViolation, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        violationUpdated.setId_car(idCar);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("violation", violationsDAO.get(idViolation));
            modelAndView.addObject("car", carDAO.get(idCar));
            modelAndView.addObject("fines", fineDAO.getAllFines());
            modelAndView.setViewName("cars/editViolation");
            return modelAndView;
        }
        violationsDAO.update(idViolation, violationUpdated);
        modelAndView.setViewName("redirect:/cars/" + idCar + "/violations");

        return modelAndView;
    }

    @DeleteMapping("/{id}/violations/{idViolation}")
    public ModelAndView deleteViolation(@PathVariable("idViolation") int idViolation, @PathVariable("id") String idCar) {
        ModelAndView modelAndView = new ModelAndView();
        violationsDAO.delete(idViolation);
        modelAndView.setViewName("redirect:/cars/" + idCar + "/violations");
        return modelAndView;
    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/edit");
        modelAndView.addObject("car", carDAO.get(id));
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("car") @Valid Car carUpdated, BindingResult bindingResult, @PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cars/edit");
            return modelAndView;
        }
        carDAO.update(id, carUpdated);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        carDAO.delete(id);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }
}