package com.netcracker.web.violations.services;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlIO implements XmlExportImport {
    CarDAOImpl carDAO;
    FineDAOImpl fineDAO;

    @Autowired
    public XmlIO(CarDAOImpl carDAO, FineDAOImpl fineDAO) {
        this.carDAO = carDAO;
        this.fineDAO = fineDAO;
    }

    @Override
    public File exportToFileCar(Car car) {
        try{
            File file = File.createTempFile("importCar", ".xml", new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\resources"));
            JAXBContext context = JAXBContext.newInstance(Car.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(car, file);
            return file;
        } catch (JAXBException | IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public File exportToFileFine(Fine fine){
        try{
            File file = File.createTempFile("importFine", ".xml", new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\resources"));
            JAXBContext context = JAXBContext.newInstance(Fine.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(fine, file);
            return file;

        } catch (JAXBException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File exportToFileViolation(Violation violation) {
        try{
            Car car = carDAO.get(violation.getId_car());
            System.out.println("Ответ: " + car.getOwner());
            Fine fine = fineDAO.get(violation.getId_fine());
            File file = File.createTempFile("importViolation", ".xml", new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\resources"));
            JAXBContext context = JAXBContext.newInstance(Fine.class, Violation.class, Car.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(violation, file);
            m.marshal(fine, file);
            m.marshal(car, file);

            return file;
        } catch (JAXBException | IOException e){
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Object importFromFile(File file) {
        return null;
    }
}
