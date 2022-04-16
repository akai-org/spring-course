package pl.org.akai.springsecurityrest.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
public class UserEntity {
    @Id
    @Column("id")
    private int id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;
}
