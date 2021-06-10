package com.netcracker.web.violations.services;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import com.netcracker.web.violations.stax.CarStaXParser;
import com.netcracker.web.violations.stax.FineStaXParser;
import com.netcracker.web.violations.stax.StaxWriter;
import com.netcracker.web.violations.stax.ViolationStaXParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class XmlIO implements XmlExportImport {
    CarDAOImpl carDAO;
    FineDAOImpl fineDAO;
    ViolationsDAOImpl violationsDAO;


    @Autowired
    public XmlIO(CarDAOImpl carDAO, FineDAOImpl fineDAO, ViolationsDAOImpl violationsDAO) {
        this.carDAO = carDAO;
        this.fineDAO = fineDAO;
        this.violationsDAO = violationsDAO;
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

    /*@Override
    public void exportToFile(String fileName) {
        StaxWriter writer = new StaxWriter(carDAO, violationsDAO, fineDAO);
        writer.staxWriter();
    }*/

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
    public void importFromFile(String fileName) {
        CarStaXParser read = new CarStaXParser();
        List<Car> cars = read.readXMLFile(fileName);
        carDAO.importFromFile(cars);
        FineStaXParser readFine = new FineStaXParser();
        List<Fine> fines = readFine.readXMLFile(fileName);
        fineDAO.importFromFile(fines);
        ViolationStaXParser readViolation = new ViolationStaXParser();
        List<Violation> violations = readViolation.readXMLFile(fileName);
        violationsDAO.importFromFile(violations);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\webapp\\res\\xml-database\\database.xslt"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\webapp\\res\\xml-database\\database.xml"));
            transformer.transform(xml, new StreamResult(new File("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\webapp\\WEB-INF\\pages\\XML\\output.html")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
