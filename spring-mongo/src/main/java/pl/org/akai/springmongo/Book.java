package pl.org.akai.springmongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("Book")
public class Book {
    @Id
    private final String id;
    private final String title;
    private final String author;
    private final Double rating;
}
