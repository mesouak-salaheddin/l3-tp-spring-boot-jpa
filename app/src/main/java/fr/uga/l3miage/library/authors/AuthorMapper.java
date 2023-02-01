package fr.uga.l3miage.library.authors;

import fr.uga.l3miage.data.domain.Author;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO entityToDTO(Author project);

    Collection<AuthorDTO> entityToDTO(Iterable<Author> project);

    Author dtoToEntity(AuthorDTO project);

    Collection<Author> dtoToEntity(Iterable<AuthorDTO> projects);
}