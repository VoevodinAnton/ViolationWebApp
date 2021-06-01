package com.netcracker.web.violations.model;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "fine")
@XmlType(propOrder = {"id", "type", "amount"})
public class Fine {
    private Integer id;
    @NotBlank(message = "Поле не может быть пустым")
    private String type;
    @NotBlank(message = "Поле не может быть пустым")
    private Integer amount;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
