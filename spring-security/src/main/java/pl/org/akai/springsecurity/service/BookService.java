package pl.org.akai.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.org.akai.springsecurity.database.BookEntity;
import pl.org.akai.springsecurity.database.BookRepository;
import pl.org.akai.springsecurity.dto.BookDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    public void updateBook(BookDto bookDto) {
        BookEntity book = Optional.ofNullable(bookDto.getId())
                                  .flatMap(bookRepository::findById)
                                  .orElse(new BookEntity());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        bookRepository.save(book);
    }
}
