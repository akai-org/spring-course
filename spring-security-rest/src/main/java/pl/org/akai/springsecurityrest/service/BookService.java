package pl.org.akai.springsecurityrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.org.akai.springsecurityrest.database.BookEntity;
import pl.org.akai.springsecurityrest.database.BookRepository;
import pl.org.akai.springsecurityrest.dto.BookDto;
import pl.org.akai.springsecurityrest.mapper.BookMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                            .map(bookMapper::fromEntity)
                            .toList();
    }

    public BookDto updateBook(BookDto bookDto) {
        BookEntity book = Optional.ofNullable(bookDto.getId())
                                  .flatMap(bookRepository::findById)
                                  .orElse(new BookEntity());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        BookEntity bookResponse = bookRepository.save(book);
        return bookMapper.fromEntity(bookResponse);
    }
}
