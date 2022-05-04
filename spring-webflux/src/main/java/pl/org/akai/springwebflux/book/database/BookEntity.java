package pl.org.akai.springwebflux.book.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("book")
public class BookEntity {
    @Id
    @Column("id")
    private Integer id;
    @Column("title")
    private String title;
    @Column("category")
    private Integer category;
    @Column("description")
    private String description;
    @LastModifiedBy
    @Column("last_modified_by")
    private String lastModifiedBy;
}
