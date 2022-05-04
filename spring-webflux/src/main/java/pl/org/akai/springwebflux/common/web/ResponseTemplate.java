package pl.org.akai.springwebflux.common.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate<T> {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("payload")
    private T payload;
    @JsonProperty("message")
    private String message;
}
