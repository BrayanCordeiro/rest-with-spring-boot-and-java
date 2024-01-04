package com.brayancordeiro.restwithspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brayancordeiro.restwithspringboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
