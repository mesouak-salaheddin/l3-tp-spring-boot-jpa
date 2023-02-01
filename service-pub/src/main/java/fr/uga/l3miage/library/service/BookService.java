package fr.uga.l3miage.library.service;

import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.base.CRUDService;

import java.util.Collection;

public interface BookService extends CRUDService<Book, Long> {

    /**
     * Find books by title. Title can partial, will be matched in case-insensitive fashion
     *
     * @param title the title of the book or a part of it (case-insensitive)
     * @return books with a matching title
     */
    Collection<Book> findByTitle(String title);

    /**
     * Get all books for a given author
     *
     * @param id the authors id
     * @return all books belonging to the author
     * @throws EntityNotFoundException if the author do not exist
     */
    Collection<Book> getByAuthor(Long id) throws EntityNotFoundException;

    /**
     * Find books for a given author
     * @param id author's id
     * @param title the title of the book or a part of it (case-insensitive)
     * @return all books belonging to the author with a matching title
     * @throws EntityNotFoundException if the author do not exist
     */
    Collection<Book> findByAuthor(Long id, String title) throws EntityNotFoundException;

}
