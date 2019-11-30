package com.ejbank.payload;

import java.io.Serializable;

public class PayloadAccount implements Serializable {
    private int id;
    private String type;
    private Double amount;

    private static final long SerialVersionUID = 1L;

    public PayloadAccount(int id, String type, Double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
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

}
