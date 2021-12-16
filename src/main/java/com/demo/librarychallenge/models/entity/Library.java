package com.demo.librarychallenge.models.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private static List<Book> books;
    private static Library instance;

    public static Library getLibrary(){
        if(instance == null){
            instance = new Library(new ArrayList<>());
            return instance;
        }else{
            return Library.getInstance();
        }
    }

    private Library(List<Book> books) {
        this.books = books;
    }

    private static Library getInstance(){
        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }
}
