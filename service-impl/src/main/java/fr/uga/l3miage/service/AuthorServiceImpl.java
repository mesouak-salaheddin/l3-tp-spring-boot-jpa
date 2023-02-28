package fr.uga.l3miage.service;

import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import fr.uga.l3miage.library.data.repo.AuthorRepository;
import fr.uga.l3miage.library.service.AuthorService;
import fr.uga.l3miage.library.service.BookService;
import fr.uga.l3miage.library.service.DeleteAuthorException;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Override
    public Collection<Author> searchByName(String name) {
        return authorRepository.searchByName(name);
    }


    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author get(Long id) throws EntityNotFoundException {
        return authorRepository.get(id);
    }


    @Override
    public Collection<Author> list() {
        return authorRepository.all();
    }

    @Override
    public Author update(Author author) throws EntityNotFoundException {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, DeleteAuthorException {
        Author author = get(id);
        if (author == null) {
            throw new EntityNotFoundException("author with id=%d not found".formatted(id));
        }
        Set<Book> books = author
                .getBooks();
        if (books != null) {

            Optional<Integer> bookWithManyAuthor = books.stream()
                    .map(Book::getAuthors)
                    .filter(Objects::nonNull)
                    .map(Collection::size)
                    .filter(s -> s > 1)
                    .findFirst();

            if (bookWithManyAuthor.isPresent()) {
                throw new DeleteAuthorException("cannot delete author, one or several books are co-authored");
            }

            for (Book book : books) {
                bookService.delete(book.getId());
            }

        }

        authorRepository.delete(author);


    }

}
