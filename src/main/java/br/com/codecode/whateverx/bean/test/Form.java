package br.com.codecode.whateverx.bean.test;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.codecode.whateverx.cdi.qualifier.Generic;
import br.com.codecode.whateverx.dao.Crud;
import br.com.codecode.whateverx.model.Sample;

@Model
public class Form {		

	@Inject @Generic
	private Crud<Sample> dao;

	private Sample sample;	

	public Form(){}

	@PostConstruct
	private void init(){
		sample = new Sample();
	}

	public Sample getUser() {
		return sample;
	}

	@Transactional
	public void save(){

		System.out.println("WhateverForm.save()");

		if(sample != null)		
			dao.saveOrUpdate(sample);							
		else
			throw new IllegalArgumentException("Whatever Entity Could not be Null");
	}

	public String goToIndex(){
		return "index.xhtml?faces-redirect=true";
	}


}
