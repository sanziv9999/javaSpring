package com.example.demo.restApiController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bRepository;
	
	
	
	@PostMapping("/api/addBook")
	public String addBook(@RequestBody Book bm) {
		
		bRepository.save(bm);
		
		return "Book added successfully";
		
	}
	
	@GetMapping("/api/getAllBooks")
	public List<Book> getAllBooks() { 
		
		List<Book> bookList = bRepository.findAll();
		return bookList;
	}
	
	@GetMapping("/api/getBookById")
	public Optional<Book> getBookById(@RequestParam int id){
		Optional<Book> bookList = bRepository.findById(id);
		
		return bookList;
	}
	
	@PutMapping("/api/updateBookById")
	public String updateBookById(@RequestBody Book bm)
	{
		bRepository.save(bm);
		return "update";
	}
	
	@DeleteMapping("/api/deleteBookById")
	public String deleteBookById(@RequestParam int id) {
		
		bRepository.deleteById(id);
		return "BookId "+id+ " "+ "deleted successfullyx";
		
	}
	
	
	
	
	
	
	
	
	

}
