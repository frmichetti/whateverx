package br.com.codecode.whateverx.bean;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.codecode.whateverx.dao.Dao;
import br.com.codecode.whateverx.model.Whatever;

@Named
public class WhateverForm {	
	
	@Inject
	private Dao<Whatever> dao ;

	private Whatever whatever = new Whatever();

	public WhateverForm(){}

	@PostConstruct
	private void init(){}

	@Transactional
	public void save(){
		if(whatever!= null)
			dao.save(whatever);
		else
			throw new IllegalArgumentException("Whatever Entity Could not be Null");
	}

	public Whatever getWhatever() {
		return whatever;
	}


}
