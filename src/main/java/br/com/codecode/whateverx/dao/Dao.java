package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public class Dao<T extends Serializable> implements Crud<T>, Serializable {

	private static final long serialVersionUID = 2314421570933641745L;

	private final Class<T> clazz;
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Dao() {
		this.clazz = (Class<T>) getClass();
	}

	public Dao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void save(T obj) {
		em.persist(obj);
	}

	@Override
	public void delete(T obj) {
		em.remove(obj);
	}

	@Override
	public void update(T obj) {
		em.merge(obj);
	}

	@Override
	public List<?> listAll() {
		
		CriteriaQuery<?> query = em.getCriteriaBuilder().createQuery(clazz);
		
		query.from(clazz);
		
		return em.createQuery(query).getResultList();
	}

	@Override
	public T findById(Long id) {		
		return em.find(clazz,id);
	}
}
