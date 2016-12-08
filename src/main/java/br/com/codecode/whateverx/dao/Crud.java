package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

public interface Crud<T extends Serializable> {

	public T save(T entity);	

	public void delete(T entity);

	public T update(T entity);
	
	public T saveOrUpdate(T entity) throws IllegalArgumentException;

	public List<T> listAll();

	public T findById(Long id);
}
