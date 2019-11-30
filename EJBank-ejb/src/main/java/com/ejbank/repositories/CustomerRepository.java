package com.ejbank.repositories;

import com.ejbank.entities.AccountEntity;
import com.ejbank.entities.CustomerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public CustomerEntity findById(int id) {
        return em.find(CustomerEntity.class, id);
    }
    public List<AccountEntity> findByUser(int userId) {
        CustomerEntity customer = em.find(CustomerEntity.class, userId);
        return customer.getAccounts();
    }
}
