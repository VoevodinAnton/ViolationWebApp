package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Fine;

import java.util.List;

public interface FineDAO {
    void save(Fine fine);

    void update(int id, Fine fine);

    Fine get(int id);

    void delete(int id);

    List<Fine> getAllFines();

    void importFromFile(List<Fine> fines);
}
