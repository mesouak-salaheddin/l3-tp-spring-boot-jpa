package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Author;
import jakarta.persistence.EntityManager;
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
     * @return une liste d'auteurs
     */
    @Override
    public List<Author> all() {
        // TODO
        return null;
    }

    /**
     *  Recherche un auteur par nom (ou partie du nom) de façon insensible  à la casse.
     * @return une liste d'auteurs
     */
    public List<Author> searchByName(String namePat) {
        // TODO
        return null;
    }

    /**
     * Recherche les auteurs ayant au moins un livre co-écrit avec un autre auteur
     * @return
     */
    public boolean findAuthorByIdHavingCoAuthoredBooks(long id) {
        // TODO
        return false;
    }

}
