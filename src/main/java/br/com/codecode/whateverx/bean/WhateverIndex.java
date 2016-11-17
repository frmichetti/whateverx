package br.com.codecode.whateverx.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.User;

@Model
public class WhateverIndex {
	
	@Inject 
	private Crud<User> dao;
	
	private List<User> users;
	
	public WhateverIndex(){}
	
	@PostConstruct
	private void init(){
		users = dao.listAll();
	}

	public List<User> getUsers() {
		return users;
	}
	
	

}
