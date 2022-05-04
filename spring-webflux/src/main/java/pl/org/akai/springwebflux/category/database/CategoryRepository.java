package pl.org.akai.springwebflux.category.database;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends R2dbcRepository<CategoryEntity, Integer> {
}
