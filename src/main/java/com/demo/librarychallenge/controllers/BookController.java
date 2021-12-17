package com.demo.librarychallenge.controllers;


import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.models.entity.QueryRequest;
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

    @PostMapping("/get{strict}")
    @ResponseBody
    public ResponseEntity getBook(@RequestBody String jsonRequest, @RequestParam(defaultValue = "true") String strict){
        try{
            QueryRequest queryRequest = bookService.getGson().fromJson(jsonRequest, QueryRequest.class);
            List<Book> books = bookService.getBook(queryRequest.getTitle(), strict);
            String response = bookService.convertToString(books);

            if(strict.equals("true")){
                System.out.println(strict);
                response = bookService.convertToString(bookService.getFilms(requestService, books));
            }

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
