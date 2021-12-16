package pl.org.akai.springmongo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/json")
    public ResponseEntity<List<BookDto>> getBooks() {
        var bookList = bookRepository.findAll()
                                     .stream()
                                     .map(BookEntityToDtoMapper::from)
                                     .toList();
         return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
