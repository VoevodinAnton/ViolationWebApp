package com.netcracker.web.violations.model;


import java.sql.Date;

public class Violation {
    private Integer id;
    private Date date;
    private Integer status;
    private String address;
    private Integer id_car;
    private Integer id_fine;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId_car() {
        return id_car;
    }

    public void setId_car(Integer id_car) {
        this.id_car = id_car;
    }

    public Integer getId_fine() {
        return id_fine;
    }

    public void setId_fine(Integer id_fine) {
        this.id_fine = id_fine;
    }
}
