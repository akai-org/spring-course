package pl.org.akai.springdata;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findDistinctByName(String name);

    @Query("SELECT * FROM book WHERE name = :name")
    Book findDistinctByNameCustom(String name);

    @Query("SELECT * FROM book WHERE name = :name")
    Book findDistinctByNameCustom2(@Param("name") String nameOfBook);

    @Query("DELETE FROM book WHERE name = :name")
    @Modifying
    int deleteBookByName(String nameOfBook);
}
