package pl.org.akai.springwebflux.book.dto;


import pl.org.akai.springwebflux.book.database.BookEntity;

public record BookListDto(Integer id,
                          String title,
                          Integer category,
                          String lastModifiedBy) {

    public BookListDto(BookEntity bookEntity) {
        this(bookEntity.id(),
                bookEntity.title(),
                bookEntity.category(),
                bookEntity.lastModifiedBy());
    }
}
