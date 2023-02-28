package fr.uga.l3miage.library.service;

import fr.uga.l3miage.library.data.domain.Book;
import fr.uga.l3miage.library.service.base.BaseService;

import java.util.Collection;

public interface BookService extends BaseService<Book, Long> {


    /**
     * Save a book by adding it to an author. Then adds the book to the author, thus implementing the bidirectional many-to-many association
     *
     * @param authorId the other id
     * @param book     the book to add
     * @return the book with an id set
     * @throws EntityNotFoundException if the author do not exist
     */
    Book save(Long authorId, Book book) throws EntityNotFoundException;

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
     *
     * @param id    author's id
     * @param title the title of the book or a part of it (case-insensitive)
     * @return all books belonging to the author with a matching title
     * @throws EntityNotFoundException if the author do not exist
     */
    Collection<Book> findByAuthor(Long id, String title) throws EntityNotFoundException;

    /**
     * Deletes a book
     *
     * @param id id of the book to delete
     * @throws EntityNotFoundException when the entity do not already exists
     */
    void delete(Long id) throws EntityNotFoundException;


    /**
     * Add an author to a book
     *
     * @param bookId   book id on witch to add the author
     * @param authorId author id to add to the book
     * @return the book updated
     * @throws EntityNotFoundException if either the book or the author is not found
     */
    Book addAuthor(Long bookId, Long authorId) throws EntityNotFoundException;
}
