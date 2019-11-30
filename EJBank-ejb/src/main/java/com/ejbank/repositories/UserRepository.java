package com.ejbank.repositories;

import com.ejbank.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public UserEntity findById(int id) {
        return em.find(UserEntity.class, id);
    }
}
