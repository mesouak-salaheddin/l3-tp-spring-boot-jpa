package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.authors.AuthorDTO;
import fr.uga.l3miage.data.domain.Book;

import java.util.Collection;

public record BookDTO(
        Long id,
        String title,
        long isbn,
        String editor,
        short year,
        Book.Language language,
        Collection<AuthorDTO> authors
) {
}
