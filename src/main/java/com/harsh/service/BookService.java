package com.harsh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.harsh.dto.BookDto;
import com.harsh.entity.Author;
import com.harsh.entity.Book;
import com.harsh.exceptions.ResourceNotFoundException;
import com.harsh.repository.AuthorRepository;
import com.harsh.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepo;
	
	private AuthorRepository authorRepo;

	public BookService(BookRepository bookRepo , AuthorRepository authorRepo) {
		super();
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}
	private BookDto convertToDTO(Book book) {
	    BookDto dto = new BookDto();
	    dto.setId(book.getId());
	    dto.setTitle(book.getBookTitle());
	    dto.setPrice(book.getBookPrice());
	    dto.setAvailable(book.isAvailable());

	    // Handle possible null author safely
	    if (book.getAuthor() != null) {
	        dto.setAuthorName(book.getAuthor().getName());
	    } else {
	        dto.setAuthorName("Unknown Author");
	    }

	    return dto;
	}


	
	
	//add a new book
	public BookDto addBook(BookDto bookDTO) {
	    // Step 1: Check if author exists, else create new
		Author author = authorRepo.findByName(bookDTO.getAuthorName());

	    // Step 2: If not found, create new author
	    if (author == null) {
	        author = new Author();
	        author.setName(bookDTO.getAuthorName());
	        author = authorRepo.save(author);
	    }

	    // Step 2: Create a new Book entity
	    Book book = new Book();
	    book.setBookTitle(bookDTO.getTitle());
	    book.setBookPrice(bookDTO.getPrice());
	    book.setAvailable(bookDTO.isAvailable());
	    book.setAuthor(author); // associate with the author

	    // Step 3: Save and return
	    Book savedBook = bookRepo.save(book);
	    return convertToDTO(savedBook);
	}

	public List<BookDto> getAllBooks() {
	    return bookRepo.findAll()
	                   .stream()
	                   .map(this::convertToDTO)
	                   .toList();
	}

	
	//get book by id
	public BookDto getBookById(Long id) {
	    Book book = bookRepo.findById(id)
	                        .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
	    return convertToDTO(book);
	}

	
	public BookDto updateBook(Long id, BookDto updatedBookDto) {
	    Book existingBook = bookRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

	    existingBook.setBookTitle(updatedBookDto.getTitle());
	    existingBook.setBookPrice(updatedBookDto.getPrice());
	    existingBook.setAvailable(updatedBookDto.isAvailable());

	    Author author = authorRepo.findByName(updatedBookDto.getAuthorName());
	    existingBook.setAuthor(author);

	    Book savedBook = bookRepo.save(existingBook);
	    return convertToDTO(savedBook);
	}

	
	public void deleteBook() {
//	    Book book = bookRepo.findById(id)
//	                        .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
	    bookRepo.deleteAll();
	}

	
	

}
