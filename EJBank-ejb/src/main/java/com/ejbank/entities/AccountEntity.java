package com.ejbank.entities;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_account")
public class AccountEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "balance")
    private double balance;
    @ManyToOne
    @JoinColumn(name = "account_type_id", referencedColumnName = "id")
    private AccountTypeEntity accountType;


    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public AccountTypeEntity getAccountType() {
        return accountType;
    }
}
