package br.com.codecode.whateverx.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DaoFactory {

	@Produces
	public Dao create(InjectionPoint injectionPoint) {

		ParameterizedType type = (ParameterizedType) injectionPoint.getType();

		Class<?> classe = (Class) type.getActualTypeArguments()[0];

		return new Dao<>(classe);
	}
}
