package br.com.codecode.whateverx.bean.test;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.codecode.whateverx.cdi.qualifier.Generic;
import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.User;

@Model
public class Form {		

	@Inject @Generic
	private Crud<User> dao;

	private User user;	

	public Form(){}

	@PostConstruct
	private void init(){
		user = new User();
	}

	public User getUser() {
		return user;
	}

	@Transactional
	public void save(){

		System.out.println("WhateverForm.save()");

		if(user != null)		
			dao.saveOrUpdate(user);							
		else
			throw new IllegalArgumentException("Whatever Entity Could not be Null");
	}

	public String goToIndex(){
		return "index.xhtml?faces-redirect=true";
	}


}
