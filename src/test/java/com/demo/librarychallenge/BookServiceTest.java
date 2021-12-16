package com.demo.librarychallenge;

import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.models.entity.Library;
import com.demo.librarychallenge.models.entity.QueryRequest;
import com.demo.librarychallenge.services.BookService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BookServiceTest {

    @Test
    public void shouldReturnEquals(){
        Book[] books = generateBooks();

        Assertions.assertEquals(books[0], books[1]);
        Assertions.assertEquals(books[0], books[2]);
        Assertions.assertNotEquals(books[0], books[3]);
    }

    @Test
    public void testListFunctions(){
        Book[] books = generateBooks();
        BookService service = new BookService(new Gson());
        service.saveToList(books[0]);
        Assertions.assertEquals(1, Library.getLibrary().getBooks().size());
        Assertions.assertEquals( books[0], Library.getLibrary().getBooks().get(0));

        service.saveToList(books[2]);
        Assertions.assertEquals(2, Library.getLibrary().getBooks().size());
        Assertions.assertEquals( books[0], Library.getLibrary().getBooks().get(0));
        Assertions.assertEquals( books[2], Library.getLibrary().getBooks().get(1));
        Assertions.assertEquals( books[0].getAuthor(), Library.getLibrary().getBooks().get(0).getAuthor());
        Assertions.assertEquals( books[2].getAuthor(), Library.getLibrary().getBooks().get(1).getAuthor());

        service.updateBook(books[4]);
        Assertions.assertEquals(2, Library.getLibrary().getBooks().size());
        Assertions.assertEquals( books[4].getAuthor(), Library.getLibrary().getBooks().get(0).getAuthor());
        Assertions.assertEquals( books[4].getAuthor(), Library.getLibrary().getBooks().get(1).getAuthor());

        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTitle("harry potter");

        service.deleteBook(service.convertToString(queryRequest));
        Assertions.assertEquals(1, Library.getLibrary().getBooks().size());
        Assertions.assertEquals( books[4].getAuthor(), Library.getLibrary().getBooks().get(0).getAuthor());

        service.saveToList(books[1]);
        service.saveToList(books[3]);
        service.saveToList(books[5]);

        queryRequest.setTitle("harry potter");

        List<Book> bookList = service.getBook(service.convertToString(queryRequest));
        Assertions.assertEquals(3, bookList.size());
        Assertions.assertEquals( books[4].getAuthor(), bookList.get(0).getAuthor());
        Assertions.assertEquals(books[1].getAuthor(), bookList.get(1).getAuthor());
    }

    private Book[] generateBooks() {
        return new Book[]{new Book("harry potter and the philosophal stone", "author1", "3", "4", "5", "6"),
                new Book("harry potter and the philosophal stone", "author2", "3", "4", "5", "6"),
                new Book("harry potter and the philosophal stone", "author3", "4", "5", "6", "7"),
                new Book("dune", "author4", "4", "5", "6", "7"),
                new Book("harry potter and the philosophal stone", "author5", "9", "9", "9", "9"),
                new Book("harry potter and the golden chalice", "author6", "9", "9", "9", "9"),
        };
    }
}
