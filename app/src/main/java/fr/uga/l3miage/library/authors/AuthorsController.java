package fr.uga.l3miage.library.authors;

import fr.uga.l3miage.data.domain.Author;
import fr.uga.l3miage.library.books.BookDTO;
import fr.uga.l3miage.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AuthorsController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    @Autowired
    public AuthorsController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/authors")
    public Collection<AuthorDTO> authors(@RequestParam(value = "q", required = false) String query) {
        Collection<Author> authors;
        if (query == null) {
            authors = authorService.list();
        } else {
            authors = authorService.searchByName(query);
        }
        return authors.stream()
                .map(authorMapper::entityToDTO)
                .toList();
    }

    public AuthorDTO author(Long id) {
        return null;
    }

    public AuthorDTO newAuthor(AuthorDTO author) {
        return null;
    }

    public AuthorDTO updateAuthor(AuthorDTO author) {
        return null;
    }

    public void deleteAuthor(Long id) {
        // unimplemented... yet!
    }

    public Collection<BookDTO> books(Long author) {
        return Collections.emptyList();
    }

}
