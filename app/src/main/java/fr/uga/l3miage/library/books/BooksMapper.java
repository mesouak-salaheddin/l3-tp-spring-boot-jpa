package fr.uga.l3miage.library.books;

import fr.uga.l3miage.data.domain.Book;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BookDTO entityToDTO(Book project);

    Collection<BookDTO> entityToDTO(Iterable<Book> project);

    Book dtoToEntity(BookDTO project);

    Collection<Book> dtoToEntity(Iterable<BookDTO> projects);
}