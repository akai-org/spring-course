package pl.org.akai.springdata;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping("/json/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") int bookId) {
        return bookRepository.findById(bookId)
                             .map(BookEntityToDtoMapper::from)
                             .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/json")
    public ResponseEntity<List<BookDto>> listBooks() {
        var books= bookRepository.findAll();
        var resultList = new LinkedList<BookDto>();
        for(Book book: books) {
            resultList.add(BookEntityToDtoMapper.from(book));
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/json")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return Optional.ofNullable(bookDto)
                       .map(BookDtoToEntityMapper::from)
                       .map(bookRepository::save)
                       .map(BookEntityToDtoMapper::from)
                       .map(book -> new ResponseEntity<>(book, HttpStatus.CREATED))
                       .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
