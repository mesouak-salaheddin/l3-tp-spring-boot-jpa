package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.data.domain.Book;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BookDTO entityToDTO(Book book);

    Collection<BookDTO> entityToDTO(Iterable<Book> books);

    Book dtoToEntity(BookDTO book);

    Collection<Book> dtoToEntity(Iterable<BookDTO> books);

    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String enumToString(Book.Language language);

    @InheritInverseConfiguration
    @ValueMapping(source = MappingConstants.NULL, target = "FRENCH")
    @ValueMapping(source = "", target = "FRENCH")
    Book.Language stringToEnum(String language);

}