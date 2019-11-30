package com.ejbank.payload;

import java.io.Serializable;

public class PayloadUser implements Serializable {
    private String firstname;
    private String lastname;

    private static final long SerialVersionUID = 1L;
    public PayloadUser(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
