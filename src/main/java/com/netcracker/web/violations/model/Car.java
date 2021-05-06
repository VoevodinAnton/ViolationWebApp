package com.netcracker.web.violations.model;

public class Car {
    private Integer id;
    private String number;
    private String model;
    private String owner;

    public Car(Integer id, String number, String model, String owner) {
        this(number, model, owner);
        this.id = id;

    }

    public Car(String number, String model, String owner) {
        this.number = number;
        this.model = model;
        this.owner = owner;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

}
