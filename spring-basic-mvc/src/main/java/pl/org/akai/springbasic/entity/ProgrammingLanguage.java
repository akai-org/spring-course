package pl.org.akai.springbasic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("PROGRAMMING_LANGUAGE")
public class ProgrammingLanguage {
    @Id
    @Column("ID")
    Integer id;

    @Column("NAME")
    String name;

    @Column("RATING")
    Double rating;
}
