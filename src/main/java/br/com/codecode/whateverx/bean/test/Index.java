package br.com.codecode.whateverx.bean.test;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.codecode.whateverx.cdi.qualifier.Generic;
import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.Sample;

@Model
public class Index {
	
	@Inject @Generic
	private Crud<Sample> dao;
	
	private List<Sample> samples;
	
	public Index(){}
	
	@PostConstruct
	private void init(){
		samples = dao.listAll();
	}

	public List<Sample> getUsers() {
		return samples;
	}
	
	public String goToForm(){
		return "form.xhtml?faces-redirect=true";
	}
	
	

}
