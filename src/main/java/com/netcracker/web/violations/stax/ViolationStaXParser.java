package com.netcracker.web.violations.stax;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;

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

public class ViolationStaXParser {
    static final String VIOLATION = "violation";
    static final String ID = "id";
    static final String ID_FINE = "id_fine";
    static final String ID_CAR = "id_car";
    static final String DATE = "date";
    static final String ADDRESS = "address";
    static final String STATUS = "status";

    @SuppressWarnings({"unchecked", "null"})
    public List<Violation> readXMLFile(String fileName){
        List<Violation> violations = new ArrayList<>();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new FileInputStream(fileName);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            Violation violation = null;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(VIOLATION)) {
                        violation = new Violation();

                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ID)) {
                        event = eventReader.nextEvent();
                        violation.setId(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ID_CAR)) {
                        event = eventReader.nextEvent();
                        violation.setId_car(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ID_FINE)) {
                        event = eventReader.nextEvent();
                        violation.setId_fine(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ADDRESS)) {
                        event = eventReader.nextEvent();
                        violation.setAddress(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(DATE)) {
                        event = eventReader.nextEvent();
                        violation.setDate(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(STATUS)) {
                        event = eventReader.nextEvent();
                        violation.setStatus(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals(VIOLATION))
                        violations.add(violation);
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return violations;
    }
}
