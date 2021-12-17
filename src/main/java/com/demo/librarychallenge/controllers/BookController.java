package com.demo.librarychallenge.controllers;


import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.services.BookService;
import com.demo.librarychallenge.services.RequestService;
import org.apache.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    BookService bookService;
    RequestService requestService;

    public BookController(BookService bookService, RequestService requestService) {
        this.bookService = bookService;
        this.requestService = requestService;
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

    @GetMapping("/get{title}{recursive}")
    public ResponseEntity getBook(@RequestParam String title, @RequestParam(defaultValue = "false") String recursive){
        try{
            List<Book> books = bookService.getBook(title);
            String response = bookService.convertToString(books);
            if(recursive.equals("false")){
                System.out.println(recursive);
                List<Book> films = requestService.request(title, bookService);
                books.addAll(films);
                response = bookService.convertToString(books);
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
