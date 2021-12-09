package pl.org.akai.springdata;

public class BookEntityToDtoMapper {

    static BookDto from(Book book) {
        return BookDto.builder()
                      .id(book.getId())
                      .name(book.getName())
                      .rating(book.getRating())
                      .build();
    }
}
