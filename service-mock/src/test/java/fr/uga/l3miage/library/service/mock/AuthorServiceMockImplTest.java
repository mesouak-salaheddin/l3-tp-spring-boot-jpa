package fr.uga.l3miage.library.service.mock;

import fr.uga.l3miage.data.domain.Author;
import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.DeleteAuthorException;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


class AuthorServiceMockImplTest {

    AuthorServiceMockImpl authorServiceMock = new AuthorServiceMockImpl();
    BookServiceMockImpl bookServiceMock = new BookServiceMockImpl(authorServiceMock);

    @Test
    void delete() throws EntityNotFoundException {

        Author author1 = new Author();
        author1.setFullName("Foo");

        Author author2 = new Author();
        author2.setFullName("Bar");

        author1 = authorServiceMock.save(author1);
        author2 = authorServiceMock.save(author2);

        Book book1 = new Book();
        book1.setTitle("Book 1");
        bookServiceMock.save(author1.getId(), book1);
        Book book2 = new Book();
        book1.setTitle("Book 2");
        book2 = bookServiceMock.save(author2.getId(), book2);
        bookServiceMock.addAuthor(book2.getId(), author1.getId());

        try {
            authorServiceMock.delete(author2.getId());
            fail("should have raised error");
        } catch (DeleteAuthorException e) {
            e.printStackTrace();
        }

        try {
            authorServiceMock.delete(author1.getId());
            fail("should have raised error");
        } catch (DeleteAuthorException e) {
            e.printStackTrace();
        }

    }
}