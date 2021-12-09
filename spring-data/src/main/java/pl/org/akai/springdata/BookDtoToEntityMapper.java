package pl.org.akai.springdata;

public class BookDtoToEntityMapper {

    static Book from(BookDto bookDto) {
        return Book.builder()
                   .name(bookDto.getName())
                   .rating(bookDto.getRating())
                   .build();
    }
}
