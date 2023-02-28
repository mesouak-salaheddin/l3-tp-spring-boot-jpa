package fr.uga.l3miage.service;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import fr.uga.l3miage.library.data.repo.BookRepository;
import fr.uga.l3miage.library.service.AuthorService;
import fr.uga.l3miage.library.service.BookService;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(AuthorService authorService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Long authorId, Book book) throws EntityNotFoundException {
        bookRepository.save(book);
        bind(authorId, book);
        return book;
    }


    @Override
    public Book get(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(bookRepository.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Cannot find book with id: " + id));
    }

    @Override
    public Collection<Book> list() {
        return bookRepository.all();
    }

    @Override
    public Book update(Book book) throws EntityNotFoundException {
        return bookRepository.save(book);
    }

    public Book addAuthor(Long bookId, Long authorId) throws EntityNotFoundException {
        var book = get(bookId);
        bind(authorId, book);
        return book;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Book book = get(id);
        bookRepository.delete(book);
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        return bookRepository.findByContainingTitle(title);
    }

    @Override
    public Collection<Book> getByAuthor(Long authorId) throws EntityNotFoundException {
        return authorService.get(authorId).getBooks();
    }

    @Override
    public Collection<Book> findByAuthor(Long authorId, String title) throws EntityNotFoundException {
        return bookRepository.findByAuthorIdAndContainingTitle(authorId, title);
    }


    private void bind(Long authorId, Book book) throws EntityNotFoundException {
        Author author = authorService.get(authorId);
        author.addBook(book);
        book.addAuthor(author);
    }


}
