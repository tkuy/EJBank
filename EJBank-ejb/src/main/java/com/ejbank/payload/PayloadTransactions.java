package com.ejbank.payload;

import java.io.Serializable;
import java.util.List;

public class PayloadTransactions implements Serializable {
    private int total;
    private List<PayloadTransactionFull> transactions;
    private String error;

    public PayloadTransactions(int total, List<PayloadTransactionFull> transactions) {
        this.total = total;
        this.transactions = transactions;
    }

    public PayloadTransactions(String error) {
        this.total = 0;
        this.transactions = null;
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public List<PayloadTransactionFull> getTransactions() {
        return transactions;
    }

    public String getError() {
        return error;
    }
}
