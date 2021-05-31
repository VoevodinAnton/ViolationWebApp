package com.netcracker.web.violations;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.stax.CarStaXParser;
import com.netcracker.web.violations.stax.FineStaXParser;
import com.netcracker.web.violations.stax.ViolationStaXParser;

import java.util.List;

//только для проверки парсера, нужно будет удалить потом
public class TestMain {
    public static void main(String[] args) {
        CarStaXParser read = new CarStaXParser();
        List<Car> cars = read.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//cars.xml");
        for(Car car: cars){
            System.out.println("Автомобиль");
            System.out.println("id " + car.getId());
            System.out.println("Номер " + car.getNumber());
            System.out.println("Модель " + car.getModel());
            System.out.println("Владелец " + car.getOwner());
        }

        FineStaXParser readFine = new FineStaXParser();
        List<Fine> fines = readFine.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//fine.xml");
        for(Fine fine: fines){
            System.out.println("Штраф");
            System.out.println("id " + fine.getId());
            System.out.println("Тип " + fine.getType());
            System.out.println("Размер " + fine.getAmount());
        }

        ViolationStaXParser readViolation = new ViolationStaXParser();
        List<Violation> violations = readViolation.readXMLFile("D://ViolationWebApp//src//main//webapp//res//xml-database//violations.xml");
        for(Violation violation: violations){
            System.out.println("Правонарушение");
            System.out.println("id " + violation.getId());
            System.out.println("id_fine " + violation.getId_fine());
            System.out.println("id_car  " + violation.getId_car());
            System.out.println("Адрес  " + violation.getAddress());
            System.out.println("Дата  " + violation.getDate());
            System.out.println("Статус  " + violation.getStatus());
        }
    }
}
