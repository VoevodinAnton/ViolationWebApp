package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;

import java.util.List;

public interface ViolationDAO {

    void save(Violation violation);

    void update(int id, Violation violation);

    Violation get(int id);

    Violation getByCar(int idCar);

    Violation getByFine(int idFine);

    void delete(int id);

    List<Violation> allViolations();

    ViolationOutput convertToOutput(Violation violation);

    List<Violation> showViolations(int id_car);

    Violation outputToViolation(ViolationOutput output);

    void importFromFile(List<Violation> violations);
}
