package com.netcracker.web.violations;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.stax.CarStaXParser;

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
    }
}
