package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.codecode.whateverx.cdi.qualifier.Production;
import br.com.codecode.whateverx.model.BaseEntity;
/**
 * Generic Implementation of {@link Crud}
 * @author felipe
 *
 * @param <T>
 */
@SuppressWarnings({"unchecked","unused"})
public class Dao<T extends BaseEntity> implements Crud<T>, Serializable {

	private static final long serialVersionUID = 2314421570933641745L;

	private final Class<?> clazz;

	/**
	 * May Change for {@link @Production} or {@link @Test} 
	 * Default is Test
	 */
	@Inject @Production
	private EntityManager em;	

	/**
	 * Only for Compatibility
	 *  ---Do not change---
	 */
	private Dao() {	
		this.clazz = (Class<?>) BaseEntity.class;	
	}

	public Dao(Class<? extends BaseEntity> clazz) {		
		this.clazz = (Class<T>) clazz;
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

		TypedQuery<?> query = em.createNamedQuery("findAll", clazz);

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
