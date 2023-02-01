package fr.uga.l3miage.library.service.mock;

import fr.uga.l3miage.data.domain.Author;
import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
    public Author addBook(Author author, Book book) {
        Author owner = get(author.getId());
        owner.addBook(book);
        book.addAuthor(author);
        return author;
    }

    @Override
    public Author save(Author author) {
        author.setId(MockData.getNextId(Author.class));
        return update(author);
    }

    @Override
    public Author get(Long id) {
        return MockData.authors.get(id);
    }

    @Override
    public Collection<Author> list() {
        return MockData.authors.values().stream().toList();
    }

    @Override
    public Author update(Author author) {
        MockData.authors.put(author.getId(), author);
        return MockData.authors.get(author.getId());
    }

    @Override
    public void delete(Long id) {
        MockData.authors.remove(id);
    }
}
