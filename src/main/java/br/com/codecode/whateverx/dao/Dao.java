package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.codecode.whateverx.model.BaseEntity;

public class Dao<T extends BaseEntity> implements Crud<T>, Serializable {

	private static final long serialVersionUID = 2314421570933641745L;

	private final Class<?> clazz;

	@PersistenceContext
	private EntityManager em;	

	/**
	 * Only for Compatibility
	 *  ---Do not change---
	 */
	private Dao() {	
		this.clazz = (Class<?>) getClass();	
	}

	public Dao(Class<? extends BaseEntity> clazz) {

		this.clazz = (Class<?>) clazz;

		System.out.println("Dao.Dao(Class<T>)");

		System.out.println(Arrays.toString(clazz.getTypeParameters()));

	}

	@Override
	public T save(BaseEntity entity) {

		em.persist(entity);

		System.out.println("Dao.save()");

		return (T) entity;
	}

	@Override
	public void delete(T entity) {

		em.remove(entity);

		System.out.println("Dao.delete()");
	}

	@Override
	public T update(T entity) {

		System.out.println("Dao.update()");

		return em.merge(entity);

	}

	@Override
	public List<?> listAll() {

		CriteriaQuery<?> query = em.getCriteriaBuilder().createQuery(clazz);

		query.from(clazz);

		System.out.println("Dao.listAll()");

		return em.createQuery(query).getResultList();
	}

	@Override
	public T findById(Long id) {	

		System.out.println("Dao.findById()");

		return (T) em.find(clazz,id);
	}

	@Override
	public T saveOrUpdate(T entity) {

		if ((entity.getId() == null) || (entity.getId() < 1)){
			throw new IllegalArgumentException("Unexpected Id");
		}

		if(entity.getId() == 0L){
			em.persist(entity);
		}else{
			em.merge(entity);
		}

		return entity;


	}
}
