package com.ejbank.payload;

import java.io.Serializable;

public class PayloadTransactionRequest implements Serializable {
    private int source;
    private int destination;
    private double amount;
    private int author;
    private String comment;


    public PayloadTransactionRequest(int source, int destination, double amount, int author) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.author = author;
    }

    public PayloadTransactionRequest() {
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
