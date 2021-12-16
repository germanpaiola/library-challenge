package com.demo.librarychallenge.services;

import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.models.entity.Library;
import com.demo.librarychallenge.models.entity.QueryRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BookService {
    private Gson gson;

    @Autowired
    public BookService(Gson gson) {
        this.gson = gson;
    }

    public Book convertToObject(String json){
        return gson.fromJson(json, Book.class);
    }

    public String convertToString(Object obj){
        return gson.toJson(obj);
    }

    public void saveToList(Book book){
        Library.getLibrary().getBooks().add(book);
    }

    public void updateBook(Book book){
        List<Book> books = Library.getLibrary().getBooks();
        for(int i = 0; i < books.size(); i++){
            if(book.equals(books.get(i))){
                books.set(i, book);
                //update all books
            }
        }
    }

    public void deleteBook(Book book){
        List<Book> books = Library.getLibrary().getBooks();
        for(int i = 0; i < books.size(); i++){
            if(book.equals(books.get(i))){
                books.remove(i);
                return;
                //delete first found;
            }
        }
    }

    public List<Book> getBook(String json){
        QueryRequest request = gson.fromJson(json, QueryRequest.class);
        List<Book> books = Library.getLibrary().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getTitle().toLowerCase(Locale.ROOT).matches(request.getTitle().toLowerCase(Locale.ROOT))){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }


}
