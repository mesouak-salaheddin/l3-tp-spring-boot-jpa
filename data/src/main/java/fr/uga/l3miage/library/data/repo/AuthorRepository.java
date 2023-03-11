package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository implements CRUDRepository<Long, Author> {

    private final EntityManager entityManager;

    @Autowired
    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author save(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author get(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void delete(Author author) {
        entityManager.remove(author);
    }

    /**
     * Renvoie tous les auteurs
     *
     * @return une liste d'auteurs trié par nom
     */
    @Override
    public List<Author> all() {
        // TODO

        return entityManager.createQuery("SELECT a FROM Author a ORDER BY a.name", Author.class)
                .getResultList();

        /*
         * return entityManager.createQuery("select * from Author", Author.class)
         * .getResultList();
         */
    }

    /**
     * Recherche un auteur par nom (ou partie du nom) de façon insensible à la
     * casse.
     *
     * @param namePart tout ou partie du nomde l'auteur
     * @return une liste d'auteurs trié par nom
     */
    public List<Author> searchByName(String namePart) {
        // TODO

        return entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.fullName LIKE CONCAT('%', :namePart, '%')",
                Author.class)
                .setParameter("namePart", namePart)
                .getResultList();
    }

    /**
     * Recherche si l'auteur a au moins un livre co-écrit avec un autre auteur
     *
     * @return true si l'auteur partage
     */
    public boolean checkAuthorByIdHavingCoAuthoredBooks(long authorId) {
        // TODO

        return entityManager.createQuery(
                "SELECT COUNT(DISTINCT a2) FROM Book b1 JOIN b1.authors a1 JOIN b1.authors a2 WHERE a1.id = :authorId AND a2.id <> :authorId",
                Long.class)
                .setParameter("authorId", authorId)
                .getSingleResult() > 0;
    }

}
