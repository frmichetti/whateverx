package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.codecode.whateverx.model.BaseEntity;
/**
 * Generic Implementation of {@link Crud}
 * @author felipe
 *
 * @param <T>
 */
@SuppressWarnings({"unused","unchecked"})
public class Dao<T extends BaseEntity> implements Crud<T>, Serializable {

	private static final long serialVersionUID = 2314421570933641745L;
	
	private Class<?> clazz;	

	private EntityManager em;	

	/**
	 * Only for Compatibility
	 *  ---Do not change---
	 */
	private Dao() {	
		System.out.println("[CDI] - Dao.Dao()");		
	}
	/**
	 * This Constructor is Required for Generify Dao 
	 * @param clazz
	 * @param em
	 */
	public Dao(Class<? extends BaseEntity> clazz, EntityManager em) {
		System.out.println("[CDI] - Dao.Dao("+clazz.getSimpleName()+")");
		System.out.println("[CDI] - EM Hash -> " + em.getClass().hashCode());
		this.clazz = (Class<T>) clazz;
		this.em = em;
	}

	@Override
	public T save(T entity) {

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
	public List<T> listAll() {

		TypedQuery<?> query = em.createNamedQuery(clazz.getSimpleName() + ".findAll", clazz);

		return (List<T>) query.getResultList();
	}

	@Override
	public T findById(Long id) {	

		System.out.println("Dao.findById()");

		return (T) em.find(clazz,id);
	}

	@Override
	public T saveOrUpdate(T entity) {

		if ((entity.getId() == null) || (entity.getId() < 0)){
			throw new IllegalArgumentException("Unexpected Id");
		}

		if(entity.getId() == 0L){
			save(entity);
		}else{
			update(entity);
		}

		return entity;

	}
}
