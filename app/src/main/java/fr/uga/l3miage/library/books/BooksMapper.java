package fr.uga.l3miage.library.books;

import fr.uga.l3miage.data.domain.Book;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BookDTO entityToDTO(Book book);

    Collection<BookDTO> entityToDTO(Iterable<Book> books);

    Book dtoToEntity(BookDTO book);

    Collection<Book> dtoToEntity(Iterable<BookDTO> books);
}