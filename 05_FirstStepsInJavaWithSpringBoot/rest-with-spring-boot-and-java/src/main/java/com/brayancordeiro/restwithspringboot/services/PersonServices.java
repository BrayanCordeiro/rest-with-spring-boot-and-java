package com.brayancordeiro.restwithspringboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.brayancordeiro.restwithspringboot.controllers.PersonController;
import com.brayancordeiro.restwithspringboot.data.vo.v1.PersonVO;
import com.brayancordeiro.restwithspringboot.exceptions.ResourceNotFoundException;
import com.brayancordeiro.restwithspringboot.mapper.DozerMapper;
import com.brayancordeiro.restwithspringboot.model.Person;
import com.brayancordeiro.restwithspringboot.repositories.PersonRepository;


@Service
public class PersonServices {

	@Autowired 
	private PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people");		
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PersonVO createPerson(PersonVO person) {
		
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;	
	}
	
	public PersonVO updatePerson(PersonVO person) {
		
		logger.info("Updating one person");
		
		var entity = repository.findById(person.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void deletePerson(Long id) {
		
		logger.info("Deleting one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}

}
