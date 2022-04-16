package pl.org.akai.springsecurityrest.mapper;

import org.mapstruct.Mapper;
import pl.org.akai.springsecurityrest.database.BookEntity;
import pl.org.akai.springsecurityrest.dto.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto fromEntity(BookEntity bookEntity);
}
