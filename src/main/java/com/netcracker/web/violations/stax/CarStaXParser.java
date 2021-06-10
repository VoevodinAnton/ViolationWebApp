package com.netcracker.web.violations.stax;

import com.netcracker.web.violations.model.Car;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CarStaXParser{
    static final String CAR = "car";
    static final String ID = "idc";
    static final String NUMBER = "number";
    static final String MODEL = "model";
    static final String OWNER = "owner";

    @SuppressWarnings({"unchecked", "null"})
    public List<Car> readXMLFile(String fileName){
        List<Car> cars = new ArrayList<>();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new FileInputStream(fileName);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream, "UTF-8");

            Car car = null;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(CAR)) {
                        car = new Car();

                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ID)) {
                        event = eventReader.nextEvent();
                        car.setId(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(NUMBER)) {
                        event = eventReader.nextEvent();
                        car.setNumber(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(MODEL)) {
                        event = eventReader.nextEvent();
                        car.setModel(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(OWNER)) {
                        event = eventReader.nextEvent();
                        car.setOwner(event.asCharacters().getData());
                        continue;
                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals(CAR))
                        cars.add(car);
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
