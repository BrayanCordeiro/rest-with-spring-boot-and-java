package com.brayancordeiro.restwithspringboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.brayancordeiro.restwithspringboot.model.Person;


@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("Finding all people");
		List<Person> persons = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	

	public Person findById(String id) {
		
		logger.info("Finding one person");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Brayan");
		person.setLastName("Cordeiro");
		person.setAdress("Paulista - PE");
		person.setGender("Male");
		
		return person;
	}
	
	public Person createPerson(Person person) {
		
		logger.info("Creating one person");
		
		return person;	
	}
	
	public Person updatePerson(Person person) {
		
		logger.info("Updating one person");
		
		return person;	
	}
	
public void deletePerson(String id) {
		
		logger.info("Deleting one person");
	}
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("FirstName " + i);
		person.setLastName("LastName " + i);
		person.setAdress("Adress " + i);
		person.setGender("Male");
		
		return person;
	}

}
