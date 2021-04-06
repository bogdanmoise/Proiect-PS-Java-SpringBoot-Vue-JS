package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
	private BookRepository bookRepository;

    @GetMapping("/books")
	public List<Book> getAllBooks() {

		return bookRepository.findAll();
	}

    @GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId){

		Book book =  bookRepository.findById(bookId).get();
		if(book == null)
        {
            return ResponseEntity.notFound().build();
        }
		return ResponseEntity.ok().body(book);
	}

    @PostMapping("/books")
	public Book createBook(@Validated @RequestBody Book book) {

		return bookRepository.save(book);
	}

    @PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId, @Validated @RequestBody Book bookDetails){

		Book book = bookRepository.findById(bookId).get();

        if(book == null)
        {
            return ResponseEntity.notFound().build();
        }

		book.setName(bookDetails.getName());
		book.setPrice(bookDetails.getPrice());
		final Book updatedBook = bookRepository.save(book);

		return ResponseEntity.ok(updatedBook);
	}

    @DeleteMapping("/books/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") Long bookId){

		Book book = bookRepository.findById(bookId).get();
		
        if(book == null)
        {
            return ResponseEntity.notFound().build();
        }

		bookRepository.delete(book);
		
		return ResponseEntity.ok(book);
	}
}
