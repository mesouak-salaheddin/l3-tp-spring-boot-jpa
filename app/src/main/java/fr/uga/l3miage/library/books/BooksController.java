package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.authors.AuthorDTO;
import fr.uga.l3miage.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class BooksController {

    private final BookService bookService;
    private final BooksMapper booksMapper;

    @Autowired
    public BooksController(BookService bookService, BooksMapper booksMapper) {
        this.bookService = bookService;
        this.booksMapper = booksMapper;
    }

    @GetMapping("/books")
    public Collection<BookDTO> books(@RequestParam("q") String query) {
        return null;
    }

    public BookDTO book(Long id) {
        return null;
    }

    public BookDTO newBook(Long authorId, BookDTO book) {
        return null;
    }

    public BookDTO updateBook(Long authorId, BookDTO book) {
        return null;
    }

    public void deleteBook(Long id) {

    }

    public void addAuthor(Long id, AuthorDTO author) {

    }
}
