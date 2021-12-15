package com.demo.librarychallenge;

import com.demo.librarychallenge.models.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {

    @Test
    public void shouldReturnEquals(){
        Book[] books = generateBooks();

        Assertions.assertEquals(books[0], books[1]);
        Assertions.assertEquals(books[0], books[2]);
        Assertions.assertNotEquals(books[0], books[3]);
    }

    private Book[] generateBooks() {
        return new Book[]{new Book("1", "2", "3", "4", "5", "6"),
                new Book("1", "2", "3", "4", "5", "6"),
                new Book("1", "3", "4", "5", "6", "7"),
                new Book("2", "3", "4", "5", "6", "7")
        };
    }
}
