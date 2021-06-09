package com.netcracker.web.violations.stax;

import com.netcracker.web.violations.dao.CarDAOImpl;
import com.netcracker.web.violations.dao.FineDAOImpl;
import com.netcracker.web.violations.dao.ViolationsDAOImpl;
import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaxWriter {
    CarDAOImpl carDAO;
    ViolationsDAOImpl violationsDAO;
    FineDAOImpl fineDAO;

    public StaxWriter(CarDAOImpl carDAO, ViolationsDAOImpl violationsDAO, FineDAOImpl fineDAO) {
        this.carDAO = carDAO;
        this.violationsDAO = violationsDAO;
        this.fineDAO = fineDAO;
    }

    public void staxWriter(List<Violation> violations){
        List<Car> cars = carDAO.getAllCars();
        violations = violationsDAO.getAllViolations();
        List<Fine> fines = fineDAO.getAllFines();
        /*List<Car> cars = new ArrayList<>();
        List<Fine> fines = new ArrayList<>();
        for(Violation violation:violations){
            cars.add(carDAO.get(violation.getId_car()));
            fines.add(fineDAO.get(violation.getId_fine()));
        }*/
        try{
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter("D:/ViolationWebApp/src/main/webapp/res/xml-database/database.xml"));

            writer.writeStartDocument("1.0");
            writer.writeStartElement("database");

            writer.writeStartElement("cars");
            for (Car car : cars) {
                writer.writeStartElement("car");

                writer.writeStartElement("idc");
                writer.writeCharacters(String.valueOf(car.getId()));
                writer.writeEndElement();


                writer.writeStartElement("number");
                writer.writeCharacters(String.valueOf(car.getNumber()));
                writer.writeEndElement();

                writer.writeStartElement("model");
                writer.writeCharacters(String.valueOf(car.getModel()));
                writer.writeEndElement();

                writer.writeStartElement("owner");
                writer.writeCharacters(String.valueOf(car.getOwner()));
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();


            writer.writeStartElement("violations");
            for (Violation violation : violations) {
                writer.writeStartElement("violation");

                writer.writeStartElement("idv");
                writer.writeCharacters(String.valueOf(violation.getId()));
                writer.writeEndElement();


                writer.writeStartElement("id_fine");
                writer.writeCharacters(String.valueOf(violation.getId_fine()));
                writer.writeEndElement();

                writer.writeStartElement("id_car");
                writer.writeCharacters(String.valueOf(violation.getId_car()));
                writer.writeEndElement();

                writer.writeStartElement("address");
                writer.writeCharacters(String.valueOf(violation.getAddress()));
                writer.writeEndElement();

                writer.writeStartElement("date");
                writer.writeCharacters(String.valueOf(violation.getDate()));
                writer.writeEndElement();

                writer.writeStartElement("status");
                writer.writeCharacters(String.valueOf(violation.getStatus()));
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();


            writer.writeStartElement("fines");
            for (Fine fine : fines) {
                writer.writeStartElement("fine");

                writer.writeStartElement("idf");
                writer.writeCharacters(String.valueOf(fine.getId()));
                writer.writeEndElement();


                writer.writeStartElement("type");
                writer.writeCharacters(String.valueOf(fine.getType()));
                writer.writeEndElement();

                writer.writeStartElement("amount");
                writer.writeCharacters(String.valueOf(fine.getAmount()));
                writer.writeEndElement();


                writer.writeEndElement();
            }
            writer.writeEndElement();



            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
        } catch (XMLStreamException | IOException e){
            e.printStackTrace();
        }
    }

}
