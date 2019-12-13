package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_customer")
@DiscriminatorValue("customer")
public class CustomerEntity extends UserEntity {
    @Column(name = "advisor_id")
    private int advisorId;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<AccountEntity> accounts;

    public int getAdvisorId() {
        return advisorId;
    }
    public List<AccountEntity> getAccounts() {
        return accounts;
    }
}
