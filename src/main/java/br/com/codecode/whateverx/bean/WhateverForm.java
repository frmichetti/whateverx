package br.com.codecode.whateverx.bean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.User;

@Named
public class WhateverForm {		

	@Inject 
	private Crud<User> dao ;

	private User user = new User();

	private List<User> users;

	public WhateverForm(){}

	@PostConstruct
	private void init(){}

	@Transactional
	public void save(){

		System.out.println("WhateverForm.save()");

		if(user != null)
		{
			System.out.println("Prepare to Save");		

			System.out.println(dao.save(user).getUuid());		

			System.out.println("Dao Type Parameter : "  + Arrays.toString(dao.getClass().getTypeParameters()));
		}			
		else
			throw new IllegalArgumentException("Whatever Entity Could not be Null");
	}

	public User getUser() {
		return user;
	}

	public List<User> getUsers() {
		return users;
	}

}
