package com.demo.librarychallenge;

import com.demo.librarychallenge.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

    @Test
    public void shouldReturnEquals(){
        Book book1 = new Book("1", "2", "3", "4", "5", "6");
        Book book2 = new Book("1", "2", "3", "4", "5", "6");
        Book book3 = new Book("1", "3", "4", "5", "6", "7");
        Book book4 = new Book("2", "3", "4", "5", "6", "7");

        Assertions.assertEquals(book1, book2);
        Assertions.assertEquals(book1, book3);
        Assertions.assertNotEquals(book1, book4);
    }
}
