package br.com.codecode.whateverx.cdi.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import br.com.codecode.whateverx.dao.Dao;
import br.com.codecode.whateverx.model.BaseEntity;

public class DaoFactory {	
	
	@Produces	
	public Dao<BaseEntity> create(InjectionPoint injectionPoint) {		

		ParameterizedType type = (ParameterizedType) injectionPoint.getType();

		Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];		

		return new Dao(clazz);
	}
}
