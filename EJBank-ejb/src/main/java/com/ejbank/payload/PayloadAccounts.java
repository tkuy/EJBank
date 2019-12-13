package com.ejbank.payload;

import java.io.Serializable;
import java.util.List;

public class PayloadAccounts implements Serializable {
    private List<PayloadAccount> accounts;
    private String error;

    public PayloadAccounts(List<PayloadAccount> accounts, String error) {
        this.accounts = accounts;
        this.error = error;
    }

    public PayloadAccounts(List<PayloadAccount> accounts) {
        this.accounts = accounts;
    }

    public List<PayloadAccount> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
