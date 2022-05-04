package pl.org.akai.springwebflux.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.org.akai.springwebflux.book.database.BookEntity;
import pl.org.akai.springwebflux.book.database.BookRepository;
import pl.org.akai.springwebflux.book.dto.BookCreateDto;
import pl.org.akai.springwebflux.book.dto.BookDetailsDto;
import pl.org.akai.springwebflux.book.dto.BookListDto;
import pl.org.akai.springwebflux.category.database.CategoryRepository;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

import static pl.org.akai.springwebflux.common.web.CommonVariable.EMPTY_QUERY_PARAM;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final ApiClient apiClient;

    public Mono<Page<BookListDto>> listBooks(Integer categoryId, PageRequest pageRequest) {
        return Mono.just(categoryId)
                   .flatMap(category -> findBooksAndCount(category, pageRequest))
                   .map(booksAndCount -> new PageImpl<>(booksAndCount.getT1(), pageRequest, booksAndCount.getT2()));
    }

    public Mono<BookDetailsDto> getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                             .flatMap(book -> categoryRepository.findById(book.category())
                                                                .map(category -> Tuples.of(book, category)))
                             .map(bookAndCategory -> new BookDetailsDto(bookAndCategory.getT1(), bookAndCategory.getT2()));
    }


    public Mono<BookDetailsDto> addBook(BookCreateDto bookDto) {
        return Mono.just(bookDto)
                   .map(this::fromDtoToEntity)
                   .flatMap(bookRepository::save)
                   .flatMap(book -> categoryRepository.findById(book.category())
                                                      .map(category -> Tuples.of(book, category)))
                   .map(bookAndCategory -> new BookDetailsDto(bookAndCategory.getT1(), bookAndCategory.getT2()));
    }

    public Mono<BookDetailsDto> updateBookById(Integer bookId, BookCreateDto bookDto) {
        return Mono.just(bookDto)
                   .map(this::fromDtoToEntity)
                   .map(book -> book.id(bookId))
                   .flatMap(bookRepository::save)
                   .flatMap(book -> categoryRepository.findById(book.category())
                                                      .map(category -> Tuples.of(book, category)))
                   .map(bookAndCategory -> new BookDetailsDto(bookAndCategory.getT1(), bookAndCategory.getT2()))
                   .onErrorResume(TransientDataAccessResourceException.class, e -> Mono.empty());
    }

    public Mono<BookDetailsDto> doRequest(Integer bookId) {
        return apiClient.doRequest(bookId);
    }

    private Mono<Tuple2<List<BookListDto>, Long>> findBooksAndCount(Integer categoryId, PageRequest pageRequest) {
        if (categoryId == EMPTY_QUERY_PARAM) {
            return bookRepository.findAllBy(pageRequest)
                                 .map(BookListDto::new)
                                 .collectList()
                                 .zipWith(bookRepository.count());
        } else {
            return bookRepository.findAllByCategory(categoryId, pageRequest)
                                 .map(BookListDto::new)
                                 .collectList()
                                 .zipWith(bookRepository.countByCategory(categoryId));
        }
    }

    private BookEntity fromDtoToEntity(BookCreateDto bookCreateDto) {
        return new BookEntity().title(bookCreateDto.title())
                               .category(bookCreateDto.category())
                               .description(bookCreateDto.description());
    }
}
