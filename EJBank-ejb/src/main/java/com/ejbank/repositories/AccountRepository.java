package com.ejbank.repositories;

import com.ejbank.entities.AccountEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public AccountEntity findById(int id) {
        return em.find(AccountEntity.class, id);
    }
    public AccountEntity update(AccountEntity accountEntity) {
        return em.merge(accountEntity);
    }
}
