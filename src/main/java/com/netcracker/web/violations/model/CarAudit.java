package com.netcracker.web.violations.model;

public class CarAudit {
    private int id;
    private int id_car;
    private String oldNumber;
    private String newNumber;
    private String oldModel;
    private String newModel;
    private String oldOwner;
    private String newOwner;
    private String typeOfEdit;
    private String dateEdit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(String oldNumber) {
        this.oldNumber = oldNumber;
    }

    public String getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(String newNumber) {
        this.newNumber = newNumber;
    }

    public String getOldModel() {
        return oldModel;
    }

    public void setOldModel(String oldModel) {
        this.oldModel = oldModel;
    }

    public String getNewModel() {
        return newModel;
    }

    public void setNewModel(String newModel) {
        this.newModel = newModel;
    }

    public String getOldOwner() {
        return oldOwner;
    }

    public void setOldOwner(String oldOwner) {
        this.oldOwner = oldOwner;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }

    public String getTypeOfEdit() {
        return typeOfEdit;
    }

    public void setTypeOfEdit(String typeOfEdit) {
        this.typeOfEdit = typeOfEdit;
    }

    public String getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(String dateEdit) {
        this.dateEdit = dateEdit;
    }
}
