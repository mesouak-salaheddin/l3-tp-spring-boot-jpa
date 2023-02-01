package fr.uga.l3miage.library.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class BooksController {


    @GetMapping("/books")
    public Collection<BookDTO> books(@RequestParam("q") String query) {
        return null;
    }

    public BookDTO book(Long id) {
        return null;
    }

    public BookDTO newBook(BookDTO author) {
        return null;
    }

    public BookDTO updateBook(BookDTO author) {
        return null;
    }

    public void deleteBook(Long id) {
        // unimplemented... yet !
    }

}
