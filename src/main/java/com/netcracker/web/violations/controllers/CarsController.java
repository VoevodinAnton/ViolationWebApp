package com.netcracker.web.violations.controllers;


import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarsController {
    private final CarDAOImpl carDAO;

    @Autowired
    public CarsController(CarDAOImpl carDAO) {
        this.carDAO = carDAO;
    }


    @GetMapping()
    public ModelAndView allCars() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/cars_page");
        modelAndView.addObject("cars", carDAO.allCars());
        return modelAndView;
    }

    @GetMapping("/{id}/violations")
    public ModelAndView showViolation(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newCar(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/new");
        return modelAndView;
    }

    //TODO: не уверен в url, место возможной ошибки
    @PostMapping()
    public ModelAndView create(@ModelAttribute("car") Car car, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cars/new");
        }
        carDAO.save(car);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/edit");
        modelAndView.addObject("car", carDAO.get(id));
        return modelAndView;
    }


}