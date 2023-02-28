package fr.uga.l3miage.library.data.repo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BookRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    void all() {

    }

    @Test
    void findByContainingTitle() {

    }

    @Test
    void findByAuthorIdAndContainingTitle() {

    }

    @Test
    void findBooksByAuthorContainingName() {

    }

    @Test
    void findBooksHavingAuthorCountGreaterThan() {

    }

}
