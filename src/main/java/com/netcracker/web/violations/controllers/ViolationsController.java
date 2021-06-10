package com.netcracker.web.violations.controllers;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import com.netcracker.web.violations.services.XmlIO;
import com.netcracker.web.violations.stax.StaxWriter;
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
    private final XmlIO xmlIO;
    private List<Violation> violationsSearch;

    @Autowired
    public ViolationsController(CarDAOImpl carDAO, ViolationsDAOImpl violationDAO, FineDAOImpl fineDAO) {
        this.carDAO = carDAO;
        this.violationDAO = violationDAO;
        this.fineDAO = fineDAO;
        this.xmlIO = new XmlIO(carDAO, fineDAO, violationDAO);
    }


    @GetMapping()
    public ModelAndView allViolations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("violations/violations_page");
        ArrayList<ViolationOutput> violations = new ArrayList<>();
        for (Violation violation : violationDAO.getAllViolations()) {
            ViolationOutput violationAdd = violationDAO.convertToOutput(violation);
            violations.add(violationAdd);
        }
        modelAndView.addObject("violations", violations);
        modelAndView.addObject("cars", carDAO.getAllCars());
        modelAndView.addObject("fines", fineDAO.getAllFines());
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
        modelAndView.addObject("cars", carDAO.getAllCars());
        modelAndView.addObject("violation", violationDAO.get(id));
        modelAndView.addObject("fines", fineDAO.getAllFines());
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@ModelAttribute("violation") @Valid Violation violationUpdated, BindingResult bindingResult, @PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()){
            modelAndView.addObject("cars", carDAO.getAllCars());
            modelAndView.addObject("violation", violationDAO.get(id));
            modelAndView.addObject("fines", fineDAO.getAllFines());
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

        ArrayList<ViolationOutput> violations = new ArrayList<>();
        violationsSearch = new ArrayList<>();
        for (Violation violation : violationList) {
            violationsSearch.add(violation);
            ViolationOutput violationAdd = violationDAO.convertToOutput(violation);
            violations.add(violationAdd);
        }

        modelAndView.addObject("violations", violations);

        modelAndView.setViewName("violations/search_page");

        return modelAndView;
    }
    @GetMapping("/import_from_file")
    public ModelAndView getAllDatabase(){
        ModelAndView modelAndView = new ModelAndView();
        xmlIO.importFromFile("D:/ViolationWebApp/src/main/webapp/res/xml-database/database.xml");
        modelAndView.setViewName("XML/output");
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView exportToFile(){
        ModelAndView modelAndView = new ModelAndView();
        StaxWriter staxWriter = new StaxWriter(carDAO, violationDAO, fineDAO);
        staxWriter.staxWriter(violationsSearch);
        modelAndView.setViewName("redirect:/violations/search");
        return modelAndView;
    }

}
