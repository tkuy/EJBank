package com.ejbank.errors;

public class TransactionException extends Exception {
    private String message;

    public TransactionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
