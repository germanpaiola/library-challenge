package com.demo.librarychallenge.services;

import com.demo.librarychallenge.models.Book;
import com.demo.librarychallenge.models.entity.Library;
import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private Gson gson;


    public Book convertToObject(String json){
        return gson.fromJson(json, Book.class);
    }

    public String convertToString(Book book){
        return gson.toJson(book);
    }

    public void saveToList(Book book){
        Library.getLibrary().getBooks().add(book);
    }

    public void updateBook(Book book){
        List<Book> books = Library.getLibrary().getBooks();
        for(Book foundBook : books){
            if(book.equals(foundBook)){
                book = foundBook;
            }
        }
    }

    public void deleteBook(Book book){
        List<Book> books = Library.getLibrary().getBooks();
        for(Book foundBook : books){
            if(book.equals(foundBook)){
                book = foundBook;
            }
        }
    }

    public List<Book> getBook(String title){
        List<Book> books = Library.getLibrary().getBooks();
        List<Book> foundBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getTitle().matches(title)){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}
