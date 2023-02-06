package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.authors.AuthorDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.util.Collection;

public record BookDTO(
        Long id,
        @NotBlank
        String title,
        @Min(1_000_000_000) // 10 digits
        @Max(9_999_999_999_999L) // 13 digits
        long isbn,
        String publisher,
        @Min(-9999) // four digit negative
        @Max(9999) // four digit positive
        short year,
        String language,
        @Null
        Collection<AuthorDTO> authors
) {
}
