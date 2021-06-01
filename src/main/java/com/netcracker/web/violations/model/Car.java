package com.netcracker.web.violations.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "car")
@XmlType(propOrder = {"id", "number", "model", "owner"})
public class Car {
    private Integer id;

    @NotBlank(message = "Поле не может быть пустым")
    @Pattern(regexp = "^[А-ЯЁ\\-\\s]{1}[0-9]{3}(?<!0{3})[А-ЯЁ\\-\\s]{2}[0-9]{3}$", message = "Неправильный номер автомобиля. \nЗапишите номер в формате 0ААА00111")
    private String number;

    @NotBlank(message = "Поле не может быть пустым")
    private String model;

    @NotBlank(message = "Поле не может быть пустым")
    private String owner;

    public Car() {

    }

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

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    @XmlElement(name = "number")
    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    @XmlElement(name = "model")
    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    @XmlElement(name = "owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

}
