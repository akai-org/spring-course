package pl.org.akai.springbasic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProgramingLanguageDto {

    @JsonProperty("ID")
    Integer id;

    @JsonProperty("NAME")
    String name;

    @JsonProperty("RATING")
    Double rating;
}
