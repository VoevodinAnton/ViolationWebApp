package com.netcracker.web.violations.services;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import org.w3c.dom.Document;

import java.io.File;

public interface XmlExportImport {
    File exportToFileCar(Car car);
    File exportToFileFine(Fine fine);
    void exportToFile(String fileName);
    void importFromFile(String fileName);
}
