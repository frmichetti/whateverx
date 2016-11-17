package br.com.codecode.whateverx.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DaoFactory {
	
	@Produces
	@Dependent
	@SuppressWarnings("rawtypes")
	public Dao create(InjectionPoint injectionPoint) {

		ParameterizedType type = (ParameterizedType) injectionPoint.getType();

		@SuppressWarnings("unchecked")
		Class<? extends Serializable> clazz = (Class<? extends Serializable>) type.getActualTypeArguments()[0];

		return new Dao<>(clazz);
	}
}
