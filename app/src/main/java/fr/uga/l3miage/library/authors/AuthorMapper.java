package fr.uga.l3miage.library.authors;

import fr.uga.l3miage.library.data.domain.Author;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO entityToDTO(Author author);

    Collection<AuthorDTO> entityToDTO(Iterable<Author> authors);

    Author dtoToEntity(AuthorDTO author);

    Collection<Author> dtoToEntity(Iterable<AuthorDTO> authors);
}