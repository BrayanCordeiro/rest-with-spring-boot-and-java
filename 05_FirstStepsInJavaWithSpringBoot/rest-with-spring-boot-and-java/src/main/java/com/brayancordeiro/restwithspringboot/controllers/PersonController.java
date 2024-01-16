package com.brayancordeiro.restwithspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brayancordeiro.restwithspringboot.data.vo.v1.PersonVO;
import com.brayancordeiro.restwithspringboot.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired 
	private PersonServices service;
	
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public List<PersonVO> findAll() {
		
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public PersonVO findById(@PathVariable(value = "id") Long id) {
		
		return service.findById(id);
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public PersonVO createPerson(@RequestBody PersonVO person) {
		
		return service.createPerson(person);
	}
	
	
	@PutMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public PersonVO updatePerson(@RequestBody PersonVO person) {
		
		return service.updatePerson(person);
	}
	
	@DeleteMapping(value = "/{id}")
		public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
		
		service.deletePerson(id);
		return ResponseEntity.noContent().build();
	}
	
}