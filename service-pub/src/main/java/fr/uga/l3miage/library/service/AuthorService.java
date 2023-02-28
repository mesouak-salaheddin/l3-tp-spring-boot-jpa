package fr.uga.l3miage.library.service;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.service.base.BaseService;

import java.util.Collection;

public interface AuthorService extends BaseService<Author, Long> {

    /**
     * Saves an author object
     *
     * @param author to be saved
     * @return the author with an id
     */
    Author save(Author author);

    /**
     * Search an author by name ignoring case
     *
     * @param name partial or complete name of the author
     * @return found authors
     */
    Collection<Author> searchByName(String name);

    /**
     * Deletes an author
     *
     * @param id id of the author to delete
     * @throws EntityNotFoundException when the entity do not already exists
     * @throws DeleteAuthorException   when an author has books that are co-authored
     */
    void delete(Long id) throws EntityNotFoundException, DeleteAuthorException;

}