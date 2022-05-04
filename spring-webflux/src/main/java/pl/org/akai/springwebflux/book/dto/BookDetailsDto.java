package pl.org.akai.springwebflux.book.dto;


import pl.org.akai.springwebflux.book.database.BookEntity;
import pl.org.akai.springwebflux.category.database.CategoryEntity;
import pl.org.akai.springwebflux.category.dto.CategoryDto;

public record BookDetailsDto(Integer id,
                             String title,
                             CategoryDto category,
                             String description,
                             String lastModifiedBy) {

    public BookDetailsDto(BookEntity bookEntity, CategoryEntity category) {
        this(bookEntity.id(),
                bookEntity.title(),
                new CategoryDto(category),
                bookEntity.description(),
                bookEntity.lastModifiedBy());
    }
}
