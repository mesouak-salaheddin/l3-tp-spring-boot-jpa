package fr.uga.l3miage.library.service.mock;

import fr.uga.l3miage.data.domain.Author;
import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.AuthorService;
import fr.uga.l3miage.library.service.DeleteAuthorException;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthorServiceMockImpl implements AuthorService {

    @Override
    public Collection<Author> searchByName(String name) {
        return MockData.authors.values()
                .stream()
                .filter(author -> author.getFullName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public Author addBook(Author author, Book book) throws EntityNotFoundException {
        Author owner = get(author.getId());
        owner.addBook(book);
        book.addAuthor(author);
        return author;
    }

    @Override
    public Author save(Author author) {
        author.setId(MockData.getNextId(Author.class));
        doUpdate(author);
        return author;
    }

    @Override
    public Author get(Long id) throws EntityNotFoundException {
        return doGet(id);
    }

    static Author doGet(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(MockData.authors.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Cannot find author with id: " + id));
    }

    @Override
    public Collection<Author> list() {
        return MockData.authors.values().stream().toList();
    }

    @Override
    public Author update(Author author) throws EntityNotFoundException {
        get(author.getId());
        doUpdate(author);
        return MockData.authors.get(author.getId());
    }

    @Override
    public void deleteAuthor(Long id) throws EntityNotFoundException, DeleteAuthorException {
        Set<Book> books = get(id)
                .getBooks();
        if (books != null) {
            books.stream()
                    .map(Book::getAuthors)
                    .filter(Objects::nonNull)
                    .map(Collection::size)
                    .filter(s -> s > 1)
                    .findFirst()
                    .orElseThrow(() -> new DeleteAuthorException("cannot delete author, one or several book are co-authored"));
        }
        MockData.authors.remove(id);

    }

    private static void doUpdate(Author author) {
        MockData.authors.put(author.getId(), author);
    }

}
