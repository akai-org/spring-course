package pl.org.akai.springwebflux.book.database;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface BookRepository extends R2dbcRepository<BookEntity, Integer> {
    Flux<BookEntity> findAllBy(Pageable pageable);
    Flux<BookEntity> findAllByCategory(int category, Pageable pageable);
    Mono<Long> countByCategory(int category);
}
