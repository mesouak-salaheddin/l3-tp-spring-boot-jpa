package fr.uga.l3miage.library.service.mock;

import fr.uga.l3miage.data.domain.Author;
import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.BookService;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class BookServiceMockImpl implements BookService {

     @Override
    public Book save(Book book) {
        book.setId(MockData.getNextId(Book.class));
        doUpdate(book);
        return book;
    }

    @Override
    public Book get(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(MockData.books.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Cannot find book with id: " + id));
    }

    @Override
    public Collection<Book> list() {
        return MockData.books.values().stream().toList();
    }

    @Override
    public Book update(Book book) throws EntityNotFoundException {
        get(book.getId());
        doUpdate(book);
        return MockData.books.get(book.getId());
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        get(id);
        MockData.books.remove(id);
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        return filterBooks(MockData.books.values(), title);
    }

    @Override
    public Collection<Book> getByAuthor(Long authorId) throws EntityNotFoundException {
        return AuthorServiceMockImpl.doGet(authorId).getBooks();
    }

    @Override
    public Collection<Book> findByAuthor(Long authorId, String title) throws EntityNotFoundException {
        AuthorServiceMockImpl.doGet(authorId);
        return filterBooks(MockData.authors.get(authorId).getBooks(), title);
    }


    private static void doUpdate(Book book) {
        MockData.books.put(book.getId(), book);
    }

    Collection<Book> filterBooks(Collection<Book> books, String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

}
