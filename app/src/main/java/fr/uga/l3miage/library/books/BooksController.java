package fr.uga.l3miage.library.books;

import fr.uga.l3miage.data.domain.Book;
import fr.uga.l3miage.library.authors.AuthorDTO;
import fr.uga.l3miage.library.service.BookService;
import fr.uga.l3miage.library.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class BooksController {

    private final BookService bookService;
    private final BooksMapper booksMapper;

    @Autowired
    public BooksController(BookService bookService, BooksMapper booksMapper) {
       this.bookService = bookService;
        this.booksMapper = booksMapper;
    }

    @GetMapping("/books")
    public Collection<BookDTO> books(@RequestParam(value = "q", required = false) String query) {
        if (Strings.isBlank(query)) {
            return booksMapper.entityToDTO(bookService.list());
        }
        return booksMapper.entityToDTO(bookService.findByTitle(query));
    }

    @GetMapping("/books/{id}")
    public BookDTO book(@PathVariable("id") @NotNull Long id) {
        try {
            return booksMapper.entityToDTO(bookService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/authors/{id}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO newBook(@PathVariable("id") @NotNull Long authorId, @RequestBody @Valid BookDTO book) {
        try {
            final var entity = bookService.save(authorId, booksMapper.dtoToEntity(book));
            return booksMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/books/{id}")
    public BookDTO updateBook(@PathVariable("id") @NotNull Long id, @RequestBody @Valid BookDTO book) {
        try {
            if (book.id().equals(id)) {
                // we have to get authors as book DTO does not have some
                var storedAuthors = bookService.get(id).getAuthors();
                Book bookEntity = booksMapper.dtoToEntity(book);
                bookEntity.setAuthors(storedAuthors);
                var updated = bookService.update(bookEntity);
                return booksMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") @NotNull Long id) {
        try {
            bookService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    /**
     * Here is the missing endpoint!
     * You do one the following "PUT" endpoint: yes it is update, you don't create anything.
     * <li><code>PUT /books/{id}/authors</code> and use the author as payload</li>
     * <li><code>PUT /authors/{id}/books</code> and use the book as payload</li>
     *
     * @param bookId the book id
     * @param author the author to add
     */
    @PutMapping("/books/{id}/authors")
    public BookDTO addAuthor(@PathVariable("id") @NotNull Long bookId, @RequestBody @Valid AuthorDTO author)  {
        try {
            if (author.id() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            return booksMapper.entityToDTO(bookService.addAuthor(bookId, author.id()));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

}
