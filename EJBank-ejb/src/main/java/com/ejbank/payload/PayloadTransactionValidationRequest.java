package com.ejbank.payload;

import java.io.Serializable;

public class PayloadTransactionValidationRequest implements Serializable {
    private int transaction;
    private boolean approve;
    private int author;

    public PayloadTransactionValidationRequest(int transaction, boolean approve, int author) {
        this.transaction = transaction;
        this.approve = approve;
        this.author = author;
    }

    public PayloadTransactionValidationRequest() {
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
