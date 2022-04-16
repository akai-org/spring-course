package pl.org.akai.springsecurityrest.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.org.akai.springsecurityrest.dto.BookDto;
import pl.org.akai.springsecurityrest.dto.LoginDto;
import pl.org.akai.springsecurityrest.service.BookService;
import pl.org.akai.springsecurityrest.service.LoginService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final LoginService loginService;
    private final BookService bookService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto tokenId) {
        LoginDto token = loginService.authenticateUser(tokenId);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/api/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        BookDto response = bookService.updateBook(bookDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
