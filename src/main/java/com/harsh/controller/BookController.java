package com.harsh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.dto.BookDto;

import com.harsh.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
		BookDto savedBook = bookService.addBook(bookDto);
		
		return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getAllBooks(){
		 List<BookDto> books = bookService.getAllBooks();
		 if (books.isEmpty()) {
			    return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(books);

		 
	}
}
