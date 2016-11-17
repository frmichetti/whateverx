package br.com.codecode.whateverx.cdi.factory;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import br.com.codecode.whateverx.cdi.qualifier.Production;
import br.com.codecode.whateverx.cdi.qualifier.Test;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {

	private static final long serialVersionUID = -1826763804778726145L;
	
	@PersistenceUnit(unitName = "WhateverDefaultDS")
	private EntityManagerFactory emfDefault ;
	
	@PersistenceUnit(unitName = "TestDS")
	private EntityManagerFactory emfTest ;

	@Dependent
	@Produces 
	@Production
	@Default
	public EntityManager getDefaultEntityManager() {
		return emfDefault.createEntityManager();
	}
	
	@Dependent
	@Produces 
	@Test
	public EntityManager getTestEntityManager() {
		return emfTest.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
}
