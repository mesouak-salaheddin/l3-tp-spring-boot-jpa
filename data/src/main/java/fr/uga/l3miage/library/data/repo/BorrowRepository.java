package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Borrow;
import fr.uga.l3miage.library.data.domain.Librarian;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowRepository implements CRUDRepository<String, Borrow> {

    private final EntityManager entityManager;

    @Autowired
    public BorrowRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Borrow save(Borrow entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Borrow get(String id) {
        return entityManager.find(Borrow.class, id);
    }

    @Override
    public void delete(Borrow entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Borrow> all() {
        return entityManager.createQuery("from Borrow", Borrow.class).getResultList();
    }

    /**
     * @param userId
     * @return
     */
    public List<Borrow> findInProgressByUser(Long userId) {
        // TODO
        return null;
    }

    /**
     *
     * @param userId
     * @return
     */
    public int countBorrowedBooksByUser(Long userId) {
        // TODO
        return 0;
    }

    /**
     *
     * @return
     */
    public List<Borrow> foundAllLateBorrow() {
        // TODO
        return null;
    }

    /**
     *
     * @param days
     * @return
     */
    public List<Borrow> foundAllBorrowThatWillBeLateInDays(int days) {
        // TODO
        return null;
    }

}
