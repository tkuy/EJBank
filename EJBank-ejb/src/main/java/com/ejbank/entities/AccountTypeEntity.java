package com.ejbank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ejbank_account_type")
public class AccountTypeEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "overdraft")
    private int overdraft;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public int getOverdraft() {
        return overdraft;
    }
}
