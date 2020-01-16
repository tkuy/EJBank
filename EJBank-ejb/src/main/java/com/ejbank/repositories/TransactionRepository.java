package com.ejbank.repositories;

import com.ejbank.entities.TransactionEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TransactionRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public TransactionEntity findById(int id) {
        return em.find(TransactionEntity.class, id);
    }

    public void create(TransactionEntity transactionEntity) {
        em.persist(transactionEntity);
    }
    public void update(TransactionEntity transactionEntity) {
        em.merge(transactionEntity);
    }

}
