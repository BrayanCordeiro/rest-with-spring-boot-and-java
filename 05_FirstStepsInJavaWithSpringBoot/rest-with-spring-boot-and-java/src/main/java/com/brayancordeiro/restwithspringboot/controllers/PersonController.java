package com.brayancordeiro.restwithspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brayancordeiro.restwithspringboot.model.Person;
import com.brayancordeiro.restwithspringboot.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired //Spring implementa private PersonServices service = new PersonServices();
	private PersonServices service;
	
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id) {
		
		return service.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) {
		
		return service.createPerson(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person) {
		
		return service.updatePerson(person);
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.DELETE)
	public void deletePerson(@PathVariable(value = "id") String id) {
		
		service.deletePerson(id);
	}
	
}