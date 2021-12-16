package com.demo.librarychallenge.controllers;


import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody String jsonRequest){
        try{
            Book book = bookService.convertToObject(jsonRequest);
            bookService.checkBook(book);
            bookService.saveToList(book);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Created");
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody String jsonRequest){
        try{
            Book book = bookService.convertToObject(jsonRequest);
            bookService.checkBook(book);
            bookService.updateBook(book);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping
    public ResponseEntity deleteBook(@RequestBody String jsonRequest){
        try{
            bookService.deleteBook(jsonRequest);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping
    public ResponseEntity getBook(@RequestBody String jsonRequest){
        try{
            List<Book> books = bookService.getBook(jsonRequest);
            String response = bookService.convertToString(books);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
