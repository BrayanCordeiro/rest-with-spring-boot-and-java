package com.brayancordeiro.restwithspringboot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayancordeiro.restwithspringboot.controllers.BookController;
import com.brayancordeiro.restwithspringboot.controllers.PersonController;
import com.brayancordeiro.restwithspringboot.data.vo.v1.BookVO;
import com.brayancordeiro.restwithspringboot.exceptions.RequiredObjectIsNullException;
import com.brayancordeiro.restwithspringboot.exceptions.ResourceNotFoundException;
import com.brayancordeiro.restwithspringboot.mapper.DozerMapper;
import com.brayancordeiro.restwithspringboot.model.Book;
import com.brayancordeiro.restwithspringboot.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository bookRepository;

	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	public List<BookVO> findAll(){
		
		logger.info("Finding all books");
		
		var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
		
		books
			.stream()
			.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return books;
	}
	
	public BookVO findById(Long id) {
		
		logger.info("Finding one book");
		
		var book = bookRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		var bookVO = DozerMapper.parseObject(book, BookVO.class);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return bookVO;
	}
	
	public BookVO createBook(BookVO vo) {
		
		if(vo == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book");
		
		var book = DozerMapper.parseObject(vo, Book.class);
		var bookVO = DozerMapper.parseObject(bookRepository.save(book), BookVO.class);
		
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	
	}
	
	public BookVO updateBook(BookVO vo) {
		
		if(vo == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one book");
		
		var book = bookRepository.findById(vo.getKey()).
				orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		book.setAuthor(vo.getAuthor());
		book.setLaunchDate(vo.getLaunchDate());
		book.setPrice(vo.getPrice());
		book.setTitle(vo.getTitle());
		
		var bookVO = DozerMapper.parseObject(bookRepository.save(book), BookVO.class);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
		
	}
	
	public void deleteBook(Long id) {
		
		logger.info("Deleting one book");
		
		var book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		bookRepository.delete(book);
		
	}
	
}
