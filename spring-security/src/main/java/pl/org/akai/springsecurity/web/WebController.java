package pl.org.akai.springsecurity.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.org.akai.springsecurity.dto.BookDto;
import pl.org.akai.springsecurity.service.BookService;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }

    @PostMapping("/")
    public String addBook(BookDto bookDto) {
        bookService.updateBook(bookDto);
        return "redirect:/";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/public/about")
    public String publicAccess() {
        return "public";
    }
}
