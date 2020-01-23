package com.ejbank.payload;

import java.io.Serializable;

public class PayloadAccount implements Serializable {
    private int id;
    private String type;
    private Double amount;
    private String user;
    private int validation;

    private static final long SerialVersionUID = 1L;

    public PayloadAccount(int id, String type, Double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public PayloadAccount(int id, String type, Double amount, String user, int validation) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.user = user;
        this.validation = validation;
    }

    public PayloadAccount(int id, String type, Double amount, String user) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUser() {
        return user;
    }

    public int getValidation() {
        return validation;
    }
}
