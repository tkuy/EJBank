package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_customer")
public class CustomerEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "advisor_id")
    private int advisorId;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<AccountEntity> accounts;

    public int getId() {
        return id;
    }
    public int getAdvisorId() {
        return advisorId;
    }
    public List<AccountEntity> getAccounts() {
        return accounts;
    }
}
