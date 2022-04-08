package pl.org.akai.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;
}
