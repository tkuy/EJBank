package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "account_id_from")
    private List<TransactionEntity> transactionsFrom;

    @OneToMany
    @JoinColumn(name = "account_id_to")
    private List<TransactionEntity> transactionsTo;

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

    public List<TransactionEntity> getTransactionsFrom() {
        return transactionsFrom;
    }

    public List<TransactionEntity> getTransactionsTo() {
        return transactionsTo;
    }
}
