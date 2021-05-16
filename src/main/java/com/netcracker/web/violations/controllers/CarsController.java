package com.netcracker.web.violations.controllers;


import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.ViolationDAO;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarsController {
    private final CarDAOImpl carDAO;
    private final ViolationsDAOImpl violationsDAO;

    @Autowired
    public CarsController(CarDAOImpl carDAO, ViolationsDAOImpl violationsDAO) {
        this.carDAO = carDAO;
        this.violationsDAO = violationsDAO;
    }


    @GetMapping()
    public ModelAndView allCars() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/cars_page");
        modelAndView.addObject("cars", carDAO.allCars());
        return modelAndView;
    }


    @GetMapping("/new")
    public ModelAndView newCar(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/new");
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView create(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        //if (bindingResult.hasErrors()) {
        //  modelAndView.setViewName("cars/new");
        // }
        carDAO.save(car);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }


    @GetMapping("/{id}/violations")
    public ModelAndView showCarViolations(@PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/car_violations");
        modelAndView.addObject("carViolations", carDAO.showViolations(idCar));
        modelAndView.addObject("car", carDAO.get(idCar));
        return modelAndView;
    }

    @GetMapping("/{id}/violations/new")
    public ModelAndView newViolation(@ModelAttribute("violation") Violation violation, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("violations/new");
        modelAndView.addObject("car", carDAO.get(idCar));
        return modelAndView;
    }

    @PostMapping("/{id}/violations/new")
    public ModelAndView createViolation(@ModelAttribute("violation") Violation violation, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        //if (bindingResult.hasErrors()) {
        //  modelAndView.setViewName("cars/new");
        // }
        violationsDAO.save(violation);
        modelAndView.setViewName("redirect:/cars/" + idCar + "/violations");
        return modelAndView;
    }

    @GetMapping("/{id}/violations/{idViolation}/edit")
    public ModelAndView updateViolation(@PathVariable("idViolation") int idViolation, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("violation", violationsDAO.get(idViolation));
        modelAndView.addObject("car", carDAO.get(id));
        modelAndView.setViewName("/cars/editViolation");
        return modelAndView;
    }

    @PatchMapping("/{id}/violations/{idViolation}")
    public ModelAndView updateViolation(@ModelAttribute("violation") ViolationOutput violationUpdated, @PathVariable("idViolation") int idViolation, @PathVariable("id") int idCar) {
        ModelAndView modelAndView = new ModelAndView();
        violationsDAO.update(idViolation, violationUpdated);
        modelAndView.setViewName("redirect:/cars/" + idCar + "/violations");

        return modelAndView;
    }

    @DeleteMapping("/{id}/violations/{idViolation}")
    public ModelAndView deleteViolation(@PathVariable("idViolation") int idViolation, @PathVariable("id") String idCar){
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
    public ModelAndView update(@ModelAttribute("car") Car carUpdated, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        carDAO.update(id, carUpdated);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        carDAO.delete(id);
        modelAndView.setViewName("redirect:/cars");
        return modelAndView;
    }
}