package com.ejbank.repositories;

import com.ejbank.entities.CustomerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public CustomerEntity findById(int id) {
        return em.find(CustomerEntity.class, id);
    }
}
