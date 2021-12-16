package pl.org.akai.springmongo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookEntityToDtoMapper {

    static BookDto from(Book book) {
        return BookDto.builder()
                      .id(book.getId())
                      .title(book.getTitle())
                      .rating(book.getRating())
                      .build();
    }
}
