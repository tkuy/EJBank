package com.ejbank.repositories;

import com.ejbank.entities.AdvisorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdvisorRepository {
    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public AdvisorEntity findById(int id) {
        return em.find(AdvisorEntity.class, id);
    }
}
