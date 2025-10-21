package com.harsh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.harsh.entity.Book;
import com.harsh.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepo;

	public BookService(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}
	
	
	//add a new book
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public List<Book> getAllBooks(){
		return bookRepo.findAll();	
		}
	
	//get book by id
	public Optional<Book> getBookById(Long id){
		return bookRepo.findById(id);
	}
	
	public Book updateBook(Long id, Book updatedBook) {
		return bookRepo.findById(id)
				.map(book -> {
					book.setBookTitle(updatedBook.getBookTitle());
					book.setAuthor(updatedBook.getAuthor());
					book.setAvailable(updatedBook.isAvailable());
					book.setBookPrice(updatedBook.getBookPrice());
					return bookRepo.save(book);
					
				})
				.orElseThrow(()-> new RuntimeException("Book not found with id:" + id));
	}
	
	public void deleteBook(Long id) {
		Book book = bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
		bookRepo.delete(book);
	}
	
	
	

}
