package com.netcracker.web.violations;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.services.XmlIO;
import com.netcracker.web.violations.stax.CarStaXParser;
import com.netcracker.web.violations.stax.FineStaXParser;
import com.netcracker.web.violations.stax.StaxWriter;
import com.netcracker.web.violations.stax.ViolationStaXParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

//только для проверки парсера, нужно будет удалить потом
public class TestMain {
    public static void main(String[] args) {
        /*CarStaXParser read = new CarStaXParser();
        List<Car> cars = read.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//database.xml");
        for(Car car: cars){
            System.out.println("Автомобиль");
            System.out.println("id " + car.getId());
            System.out.println("Номер " + car.getNumber());
            System.out.println("Модель " + car.getModel());
            System.out.println("Владелец " + car.getOwner());
        }

        FineStaXParser readFine = new FineStaXParser();
        List<Fine> fines = readFine.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//database.xml");
        for(Fine fine: fines){
            System.out.println("Штраф");
            System.out.println("id " + fine.getId());
            System.out.println("Тип " + fine.getType());
            System.out.println("Размер " + fine.getAmount());
        }

        ViolationStaXParser readViolation = new ViolationStaXParser();
        List<Violation> violations = readViolation.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//database.xml");
        for(Violation violation: violations){
            System.out.println("Правонарушение");
            System.out.println("id " + violation.getId());
            System.out.println("id_fine " + violation.getId_fine());
            System.out.println("id_car  " + violation.getId_car());
            System.out.println("Адрес  " + violation.getAddress());
            System.out.println("Дата  " + violation.getDate());
            System.out.println("Статус  " + violation.getStatus());
        }*/
       /* try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("D://ViolationWebApp//src//main//webapp//res//xml-database//database.xslt"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("D://ViolationWebApp//src//main//webapp//res//xml-database//database.xml"));
            transformer.transform(xml, new StreamResult(new File("D://ViolationWebApp//src//main//webapp//res//xml-database//output.html")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }*/



        CarDAOImpl carDAO = new CarDAOImpl();
        FineDAOImpl fineDAO = new FineDAOImpl();
        ViolationsDAOImpl violationsDAO = new ViolationsDAOImpl();
        /*StaxWriter staxWriter = new StaxWriter(carDAO, violationsDAO, fineDAO);
        staxWriter.staxWriter();*/

        XmlIO xmlIO = new XmlIO(carDAO, fineDAO, violationsDAO);
        xmlIO.importFromFile("src/main/webapp/res/xml-database/database.xml");
    }
}
