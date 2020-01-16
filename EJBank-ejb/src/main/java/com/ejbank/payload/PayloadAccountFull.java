package com.ejbank.payload;

import java.io.Serializable;

public class PayloadAccountFull implements Serializable {
    private String owner;
    private String advisor;
    private Double rate;
    private Double interest;
    private Double amount;
    private String error;

    private static final long SerialVersionUID = 1L;

    public PayloadAccountFull(String owner, String advisor, Double rate, Double interest, Double amount) {
        this.owner = owner;
        this.advisor = advisor;
        this.rate = rate;
        this.interest = interest;
        this.amount = amount;
    }

    public PayloadAccountFull(String err) {
        this.owner = "";
        this.advisor = "";
        this.rate = 0.;
        this.interest = 0.;
        this.amount = 0.;
        this.error = err;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public Double getRate() {
        return rate;
    }

    public Double getInterest() {
        return interest;
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public Double getAmount() {
        return amount;
    }

}
