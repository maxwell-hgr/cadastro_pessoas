package org.sinerji.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityMenagerProducer {

    private EntityManagerFactory entityManagerFactory;

    public EntityMenagerProducer(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("SinerjiPU");
    }

    @Produces
    @RequestScoped
    public EntityManager createEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager em){
        em.close();
    }
}
