package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
class BorrowRepositoryTest extends Base {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BorrowRepository repository;
    private Book b1;
    private Book b2;
    private Book b3;
    private User u1;
    private User u2;
    private Librarian l1;

    @BeforeEach
    void setupInventory() {
        Author a1 = Fixtures.newAuthor();
        Author a2 = Fixtures.newAuthor();
        Author a3 = Fixtures.newAuthor();

        this.b1 = Fixtures.newBook();
        this.b2 = Fixtures.newBook();
        this.b3 = Fixtures.newBook();
        a1.addBook(b1);
        a2.addBook(b2);
        a3.addBook(b3);
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        b1.addAuthor(a1);
        b2.addAuthor(a2);
        b3.addAuthor(a3);
        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(b3);

        this.u1 = Fixtures.newUser();
        this.u2 = Fixtures.newUser();
        this.l1 = Fixtures.newLibrarian();

        entityManager.persist(u1);
        entityManager.persist(u2);
        entityManager.persist(l1);

        entityManager.flush();
    }

    @Test
    void findInProgressByUser() {

        Borrow inProgress = Fixtures.newBorrow(u1, l1, b1, b2);
        Borrow finished = Fixtures.newBorrow(u1, l1, b3);
        finished.setRequestedReturn(new Date());
        finished.setFinished(true);
        entityManager.persist(inProgress);
        entityManager.persist(finished);
        entityManager.flush();

        List<Borrow> progressByUser = repository.findInProgressByUser(u1.getId());
        assertThat(progressByUser).containsExactly(inProgress);

    }

    @Test
    void countCurrentBorrowedBooksByUser() {

        // TODO

    }

    @Test
    void countBorrowedBooksByUser() {

        // TODO

    }

    @Test
    void foundAllLateBorrow() {

        // TODO

    }

    @Test
     void foundAllBorrowThatWillBeLateInDays() {

        Borrow lateIn5Days = Fixtures.newBorrow(u1, l1, b1);
        lateIn5Days.setRequestedReturn(Date.from(ZonedDateTime.now().plus(5, ChronoUnit.DAYS).toInstant()));
        Borrow lateIn10Days = Fixtures.newBorrow(u1, l1, b2);
        lateIn10Days.setRequestedReturn(Date.from(ZonedDateTime.now().plus(10, ChronoUnit.DAYS).toInstant()));
        Borrow lateIn15Days = Fixtures.newBorrow(u2, l1, b3);
        lateIn15Days.setRequestedReturn(Date.from(ZonedDateTime.now().plus(15, ChronoUnit.DAYS).toInstant()));

        entityManager.persist(lateIn5Days);
        entityManager.persist(lateIn10Days);
        entityManager.persist(lateIn15Days);
        entityManager.flush();

        List<Borrow> borrows = repository.findAllBorrowThatWillLateWithin(12);
        assertThat(borrows).containsExactlyInAnyOrder(lateIn5Days, lateIn10Days);

    }

}
