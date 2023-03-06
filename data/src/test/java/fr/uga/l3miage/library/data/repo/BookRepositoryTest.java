package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookRepository bookRepository;

    @Test
    void all() {

        Book b1 = Fixtures.newBook();
        b1.setTitle("c");
        Book b2 = Fixtures.newBook();
        b2.setTitle("b");
        Book b3 = Fixtures.newBook();
        b3.setTitle("a");
        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(b3);

        entityManager.flush();
        entityManager.detach(b1);
        entityManager.detach(b2);
        entityManager.detach(b3);

        List<Book> books = bookRepository.all();
        assertThat(books)
                .hasSize(3)
                .extracting("title")
                .containsExactly("a", "b", "c");

    }

    @Test
    void findByContainingTitle() {

        Book b1 = Fixtures.newBook();
        b1.setTitle("Hello world");
        Book b2 = Fixtures.newBook();
        b2.setTitle("Please say hello!");
        Book b3 = Fixtures.newBook();
        b3.setTitle("I'm an old smock");

        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(b3);

        entityManager.flush();
        entityManager.detach(b1);
        entityManager.detach(b2);
        entityManager.detach(b3);

        var res = bookRepository.findByContainingTitle("hello");
        assertThat(res)
                .hasSize(2)
                .extracting("title")
                .containsExactly("Hello world", "Please say hello!");

    }

    @Test
    void findByAuthorIdAndContainingTitle() {
        Book b1 = Fixtures.newBook();
        b1.setTitle("Hello world");
        Book b2 = Fixtures.newBook();
        b2.setTitle("Please say hello!");
        Book b3 = Fixtures.newBook();
        b3.setTitle("I'm an old smock");


        Author a1 = Fixtures.newAuthor();
        Author a2 = Fixtures.newAuthor();
        a1.addBook(b1);
        a1.addBook(b2);
        a2.addBook(b3);
        entityManager.persist(a1);
        entityManager.persist(a2);
        b1.addAuthor(a1);
        b2.addAuthor(a1);
        b3.addAuthor(a2);
        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(b3);

        entityManager.flush();

        entityManager.detach(a1);
        entityManager.detach(a2);
        entityManager.detach(b1);
        entityManager.detach(b2);
        entityManager.detach(b3);

        var res = bookRepository.findByAuthorIdAndContainingTitle(a1.getId(), "hello");
        assertThat(res)
                .hasSize(2)
                .extracting("title")
                .containsExactly("Hello world", "Please say hello!");
    }

    @Test
    void findBooksByAuthorContainingName() {

        Book b1 = Fixtures.newBook();
        Book b1_2 = Fixtures.newBook();
        {
            Author a1 = Fixtures.newAuthor();
            a1.setFullName("Stephen King");
            a1.addBook(b1_2);
            a1.addBook(b1);
            b1.addAuthor(a1);
            b1_2.addAuthor(a1);
            entityManager.persist(a1);
            entityManager.persist(b1);
            entityManager.persist(b1_2);
            entityManager.flush();
            entityManager.detach(b1);
            entityManager.detach(b1_2);
            entityManager.detach(a1);

        }
        {
            Author a2 = Fixtures.newAuthor();
            a2.setFullName("Victor Hugo");
            Book b2 = Fixtures.newBook();
            a2.addBook(b2);
            entityManager.persist(a2);
            b2.addAuthor(a2);
            entityManager.persist(b2);
            entityManager.flush();

            entityManager.detach(b2);
            entityManager.detach(a2);
        }
        List<Book> kingsBooks = bookRepository.findBooksByAuthorContainingName("king");
        assertThat(kingsBooks)
                .hasSize(2)
                .extracting("title")
                .containsExactlyInAnyOrder(b1.getTitle(), b1_2.getTitle());

    }

    @Test
    void findBooksHavingAuthorCountGreaterThan() {

        Author a1 = Fixtures.newAuthor();
        Author a2 = Fixtures.newAuthor();
        Author a3 = Fixtures.newAuthor();

        Book b1 = Fixtures.newBook();
        Book b2 = Fixtures.newBook();
        a1.addBook(b1);
        a2.addBook(b2);
        a3.addBook(b2);
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        b1.addAuthor(a1);
        b2.addAuthor(a2);
        b2.addAuthor(a3);
        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.flush();

        entityManager.detach(b1);
        entityManager.detach(b2);
        entityManager.detach(a1);
        entityManager.detach(a2);
        entityManager.detach(a3);

        List<Book> coAuthoredBooks = bookRepository.findBooksHavingAuthorCountGreaterThan(1);
        assertThat(coAuthoredBooks)
                .hasSize(1)
                .extracting("title")
                .containsExactly(b2.getTitle());

    }

}
