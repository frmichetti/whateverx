package br.com.codecode.whateverx.bean.test;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.User;

@Model
public class Index {
	
	@Inject 
	private Crud<User> dao;
	
	private List<User> users;
	
	public Index(){}
	
	@PostConstruct
	private void init(){
		users = dao.listAll();
	}

	public List<User> getUsers() {
		return users;
	}
	
	public String goToForm(){
		return "form.xhtml?faces-redirect=true";
	}
	
	

}
