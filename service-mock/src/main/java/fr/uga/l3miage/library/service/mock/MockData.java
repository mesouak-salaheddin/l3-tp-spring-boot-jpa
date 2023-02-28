package fr.uga.l3miage.library.service.mock;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockData {
    static Map<Long, Author> authors = new HashMap<>();
    static Map<Long, Book> books = new HashMap<>();
    private static long nextBookId;
    private static long nextAuthorId;

    private MockData() {
        // to hide the public one
    }

    public static <T> long getNextId(Class<T> c) {
        if (c.equals(Book.class)) {
            return nextBookId++;
        } else {
            return nextAuthorId++;
        }
    }

    static {

        Author me = new Author();
        me.setId(-1L);
        me.setFullName("Benoit Bordigoni");

        Book jpa = new Book();
        jpa.setId(-1L);
        jpa.setTitle("The Art of JPA");
        jpa.setIsbn(2145673168735453L);
        jpa.setPublisher("Dunod");
        jpa.setYear((short) 2023);
        jpa.setLanguage(Book.Language.ENGLISH);

        jpa.addAuthor(me);
        me.addBook(jpa);

        authors.put(me.getId(), me);
        books.put(jpa.getId(), jpa);

    }

}
