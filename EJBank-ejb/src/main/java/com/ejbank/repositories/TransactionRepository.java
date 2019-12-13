package com.ejbank.repositories;

import com.ejbank.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;

@Stateless
public class TransactionRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public Transaction findById(int id) {
        return em.find(Transaction.class, id);
    }
}
