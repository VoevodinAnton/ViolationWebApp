package com.netcracker.web.violations.model;


import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Date;

@XmlRootElement(name = "violation")
@XmlType(propOrder = {"id", "date", "status", "address", "id_car", "id_fine"})
public class Violation {
    private Integer id;
    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Дата должна быть введена в формате ГГГГ-ММ-ДД")
    private String date;
    private Integer status;
    @NotBlank(message = "Поле не может быть пустым")
    private String address;
    private Integer id_car;
    private Integer id_fine;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId_car() {
        return id_car;
    }

    @XmlElement(name = "id_car")
    public void setId_car(Integer id_car) {
        this.id_car = id_car;
    }

    public Integer getId_fine() {
        return id_fine;
    }

    @XmlElement(name = "id_fine")
    public void setId_fine(Integer id_fine) {
        this.id_fine = id_fine;
    }
}
