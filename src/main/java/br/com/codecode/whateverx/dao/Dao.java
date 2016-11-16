package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class Dao<T>  implements Serializable {

	private static final long serialVersionUID = 2314421570933641745L;

	private final Class<T> classe;

	private EntityManager em;

	public Dao(Class<T> classe) {
		this .classe = classe;
	}

	public void save(T obj) {
		em.persist(obj);
	}

	public void delete(T obj) {
		em.remove(obj);
	}

	public void update(T obj) {
		em.merge(obj);
	}

	public List<?> listAll() {
		CriteriaQuery<?> query = em.getCriteriaBuilder().createQuery(classe);
		query.from(classe);
		return em.createQuery(query).getResultList();
	}

	public T findById(Long id) {
		return em.find(classe,id);
	}
}
