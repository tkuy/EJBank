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

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(AccountTypeEntity accountType) {
        this.accountType = accountType;
    }

    public void setTransactionsFrom(List<TransactionEntity> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public void setTransactionsTo(List<TransactionEntity> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }
}
