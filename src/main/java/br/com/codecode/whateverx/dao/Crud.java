package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.util.List;

public interface Crud<T extends Serializable> {

	public void save(T obj);	

	public void delete(T obj);

	public void update(T obj);

	public List<?> listAll();

	public T findById(Long id);
}
