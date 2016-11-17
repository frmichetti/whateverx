package br.com.codecode.whateverx.cdi.producer;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import br.com.codecode.whateverx.dao.Dao;
import br.com.codecode.whateverx.model.BaseEntity;

/**
 * Factory for Generic {@link Dao} Injection
 * 
 * @author felipe
 *
 */
@SuppressWarnings("unchecked")
public class DaoProducer {	
	
	@Produces	
	public Dao<BaseEntity> getDao(InjectionPoint injectionPoint) {		

		ParameterizedType type = (ParameterizedType) injectionPoint.getType();

		Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];		

		return new Dao<BaseEntity>((Class<? extends BaseEntity>) clazz);
	}
}
