package com.planner.producers;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer implements Serializable {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactoryMySQL() {
	return Persistence.createEntityManagerFactory("selecaoPlanner");
    }

    /**
     * 
     */
    private static final long serialVersionUID = -7940309802233350557L;
    @PersistenceContext(unitName = "selecaoPlanner")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
	return entityManagerFactory.createEntityManager();
    }

    protected void closeEntityManager(@Disposes EntityManager entityManager) {
	if (entityManager.isOpen()) {
	    entityManager.close();
	}
    }
}
