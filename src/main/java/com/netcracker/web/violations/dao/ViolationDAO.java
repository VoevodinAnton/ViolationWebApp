package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;

import java.util.List;

public interface ViolationDAO {

    void save(Violation violation);

    void update(int id, Violation violation);

    Violation get(int id);

    Violation getByCar(int idCar);

    Violation getByFine(int idFine);

    void delete(int id);

    List<Violation> allViolations();
}
