package com.brayancordeiro.restwithspringboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayancordeiro.restwithspringboot.exceptions.ResourceNotFoundException;
import com.brayancordeiro.restwithspringboot.model.Person;
import com.brayancordeiro.restwithspringboot.repositories.PersonRepository;


@Service
public class PersonServices {

	@Autowired 
	private PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("Finding all people");		
		
		return repository.findAll();
	}
	

	public Person findById(Long id) {
		
		logger.info("Finding one person");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Person createPerson(Person person) {
		
		logger.info("Creating one person");
		
		return repository.save(person);	
	}
	
	public Person updatePerson(Person person) {
		
		logger.info("Updating one person");
		
		var entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void deletePerson(Long id) {
		
		logger.info("Deleting one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}

}
