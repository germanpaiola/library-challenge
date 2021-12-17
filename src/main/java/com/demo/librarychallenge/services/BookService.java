package com.demo.librarychallenge.services;

import com.demo.librarychallenge.exceptions.InvalidRequestException;
import com.demo.librarychallenge.exceptions.NotFoundException;
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

    public List convertToList(String json){
        return gson.fromJson(json, ArrayList.class);
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
                return;
                //update first book found
            }
        }
        throw new NotFoundException("Book not found for title.");
    }

    public void deleteBook(String json){
        QueryRequest request = gson.fromJson(json, QueryRequest.class);
        checkRequest(request);
        List<Book> books = Library.getLibrary().getBooks();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(request.getTitle())){
                books.remove(i);
                return;
                //delete first found;
            }
        }
        throw new NotFoundException("Book not found for title.");
    }

    public List<Book> getBook(String title){

        List<Book> books = Library.getLibrary().getBooks();
        List<Book> foundBooks = new ArrayList<>();

        for(Book book : books){
            if(book.getTitle().toLowerCase().contains(title)){
                foundBooks.add(book);

            }
        }
        if(foundBooks.size() == 0){
            throw new NotFoundException("Book not found for title.");
        }
        return foundBooks;
    }

    public void checkRequest(QueryRequest req){
        if(req.getTitle() == null){
            throw new InvalidRequestException("Invalid Request");
        }
    }

    public void checkBook(Book book){
        if(book.getTitle() == null){
            throw new InvalidRequestException("Invalid Request");
        }
    }
}
