package fr.uga.l3miage.library.data.repo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PersonRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    LibrarianRepository librarianRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findInProgressByUser() {

    }

    @Test
    void countBorrowedBooksByUser() {

    }

    @Test
    void foundAllLateBorrow() {

    }

    @Test
    void foundAllBorrowThatWillBeLateInDays() {

    }

}