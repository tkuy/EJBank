package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue("advisor")
public class AdvisorEntity extends UserEntity {
    @OneToMany
    @JoinColumn(name = "advisor_id")
    private List<CustomerEntity> customers;

    public List<CustomerEntity> getCustomers() {
        return customers;
    }
}
