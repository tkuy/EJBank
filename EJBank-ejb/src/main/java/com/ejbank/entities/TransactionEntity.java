package com.ejbank.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ejbank_transaction")
public class TransactionEntity {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "account_id_from", referencedColumnName = "id")
    private AccountEntity src;
    @ManyToOne
    @JoinColumn(name = "account_id_to", referencedColumnName = "id")
    private AccountEntity dest;
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private UserEntity author;
    @Column(name = "amount")
    private double amount;
    @Column(name = "comment")
    private String comment;
    @Column(name = "applied")
    private boolean applied;
    @Column(name = "date")
    private Date date;

    public TransactionEntity() {
    }

    public TransactionEntity(int id, AccountEntity src, AccountEntity dest, UserEntity author, double amount, String comment, boolean applied, Date date) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.author = author;
        this.amount = amount;
        this.comment = comment;
        this.applied = applied;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountEntity getSrc() {
        return src;
    }

    public void setSrc(AccountEntity src) {
        this.src = src;
    }

    public AccountEntity getDest() {
        return dest;
    }

    public void setDest(AccountEntity dest) {
        this.dest = dest;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
