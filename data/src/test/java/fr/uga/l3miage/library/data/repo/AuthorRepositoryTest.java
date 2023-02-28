package fr.uga.l3miage.library.data.repo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class AuthorRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AuthorRepository repository;

    @Test
    void searchByName() {

    }

    @Test
    void findAuthorByIdHavingCoAuthoredBooks() {

    }

}
