package pl.org.akai.springwebflux.book.dto;


public record BookCreateDto(String title,
                            Integer category,
                            String description) {
}
