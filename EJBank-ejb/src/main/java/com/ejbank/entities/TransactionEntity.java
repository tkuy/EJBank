package com.ejbank.entities;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_transaction")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
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

    public int getId() {
        return id;
    }

    public AccountEntity getSrc() {
        return src;
    }

    public AccountEntity getDest() {
        return dest;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public boolean isApplied() {
        return applied;
    }
}
