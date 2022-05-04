package pl.org.akai.springwebflux.category.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("category")
public class CategoryEntity {
    @Id
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
}
