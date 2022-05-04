package pl.org.akai.springwebflux.category.dto;


import pl.org.akai.springwebflux.category.database.CategoryEntity;

public record CategoryDto(Integer id,
                          String name) {

    public CategoryDto(CategoryEntity category) {
        this(category.id(), category.name());
    }
}
