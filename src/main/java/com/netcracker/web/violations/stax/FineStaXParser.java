package com.netcracker.web.violations.stax;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;

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

public class FineStaXParser {
    static final String FINE = "fine";
    static final String ID = "idf";
    static final String TYPE = "type";
    static final String AMOUNT = "amount";

    @SuppressWarnings({"unchecked", "null"})
    public List<Fine> readXMLFile(String fileName){
        List<Fine> fines = new ArrayList<>();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new FileInputStream(fileName);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream, "UTF-8");

            Fine fine = null;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(FINE)) {
                        fine = new Fine();
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ID)) {
                        event = eventReader.nextEvent();
                        fine.setId(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(TYPE)) {
                        event = eventReader.nextEvent();
                        fine.setType(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(AMOUNT)) {
                        event = eventReader.nextEvent();
                        fine.setAmount(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals(FINE))
                        fines.add(fine);
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return fines;
    }
}
