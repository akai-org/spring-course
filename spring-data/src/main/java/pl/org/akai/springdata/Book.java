package pl.org.akai.springdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("rating")
    private Double rating;
}
