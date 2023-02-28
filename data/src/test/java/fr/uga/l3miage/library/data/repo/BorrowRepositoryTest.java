package fr.uga.l3miage.library.data.repo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BorrowRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BorrowRepository repository;

    @Test
    public void findInProgressByUser() {

    }

    @Test
    public void countBorrowedBooksByUser() {

    }

    @Test
    public void foundAllLateBorrow() {

    }

    @Test
    public void foundAllBorrowThatWillBeLateInDays() {

    }

}
