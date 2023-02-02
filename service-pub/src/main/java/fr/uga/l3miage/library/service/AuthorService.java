package fr.uga.l3miage.library.service;

import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.base.CRUDService;
import fr.uga.l3miage.data.domain.Author;

import java.util.Collection;

public interface AuthorService extends CRUDService<Author, Long> {

    /**
     * Search an author by name ignoring case
     * @param name partial or complete name of the author
     * @return found authors
     */
    Collection<Author> searchByName(String name);

    /**
     * Add a book to an author, thus implementing the bidirectional many-to-many association
     * @param author the author
     * @param book the book to add
     * @return an updated {@link Author}
     */
    Author addBook(Author author, Book book) throws EntityNotFoundException;

    /**
     * @deprecated use {@link #deleteAuthor(Long)} instead
     */
    @Override
    @Deprecated
    default void delete(Long id) {
        throw new UnsupportedOperationException("use deleteAuthor(Long) instead");
    }

    /**
     * Deletes an author
     * @param id id of the author to delete
     * @throws EntityNotFoundException when the entity do not already exists
     * @throws DeleteAuthorException when an author has books that are co-authored
     */
    void deleteAuthor(Long id) throws EntityNotFoundException, DeleteAuthorException;
}
