package com.brayancordeiro.restwithspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brayancordeiro.restwithspringboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{}
