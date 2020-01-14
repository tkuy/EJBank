package com.ejbank.payload;

import java.io.Serializable;

public class PayloadTransaction implements Serializable {
    private boolean result;
    private int before;
    private int after;
    private String message;
    private String error;

    public PayloadTransaction(boolean result, int before, int after, String message, String error) {
        this.result = result;
        this.before = before;
        this.after = after;
        this.message = message;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getAfter() {
        return after;
    }

    public void setAfter(int after) {
        this.after = after;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "PayloadTransaction{" +
                "result=" + result +
                ", before=" + before +
                ", after=" + after +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
