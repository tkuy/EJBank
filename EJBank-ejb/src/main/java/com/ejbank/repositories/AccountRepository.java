package com.ejbank.repositories;

import com.ejbank.entities.AccountEntity;
import com.ejbank.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public AccountEntity findById(int id) {
        return em.find(AccountEntity.class, id);
    }
    public List<AccountEntity> findByUser(int userId) {
        return null; //TODO
    }
}
