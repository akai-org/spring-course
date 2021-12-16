package pl.org.akai.springmongo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDtoToEntityMapper {

    static Book from(BookDto bookDto) {
        return Book.builder()
                   .title(bookDto.getTitle())
                   .rating(bookDto.getRating())
                   .build();
    }
}
