package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorRepositoryTest extends Base {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void searchByName() {

        Author a1 = Fixtures.newAuthor();
        a1.setFullName("William Gibson");
        Author a2 = Fixtures.newAuthor();
        a2.setFullName("Arthur Hemingway");
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.flush();
        entityManager.detach(a1);
        entityManager.detach(a2);

        List<Author> authors = authorRepository.searchByName("Will");
        assertThat(authors).containsExactly(a1);

    }

    @Test
    void findAuthorByIdHavingCoAuthoredBooks() {

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

        assertThat(authorRepository.checkAuthorByIdHavingCoAuthoredBooks(a1.getId())).isFalse();
        assertThat(authorRepository.checkAuthorByIdHavingCoAuthoredBooks(a2.getId())).isTrue();

    }

}
