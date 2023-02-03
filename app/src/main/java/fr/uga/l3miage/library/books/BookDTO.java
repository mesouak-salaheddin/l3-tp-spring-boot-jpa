package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.authors.AuthorDTO;

import java.util.Collection;

public record BookDTO(
        Long id,
        String title,
        long isbn,
        String publisher,
        short year,
        String language,
        Collection<AuthorDTO> authors
) {
}
