package com.netcracker.web.violations.model;

import javax.validation.constraints.NotBlank;

public class Fine {
    private Integer id;
    @NotBlank(message = "Поле не может быть пустым")
    private String type;
    @NotBlank(message = "Поле не может быть пустым")
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
