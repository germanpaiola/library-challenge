package com.demo.librarychallenge.controllers;


import com.demo.librarychallenge.models.Book;
import com.demo.librarychallenge.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            bookService.saveToList(book);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok("Created");
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody String jsonRequest){
        try{

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping
    public ResponseEntity deleteBook(@RequestBody String jsonRequest){
        try{

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping
    public ResponseEntity getBook(@RequestBody String jsonRequest){
        try{








            return ResponseEntity.ok("Created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
