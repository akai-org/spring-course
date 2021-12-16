package pl.org.akai.springdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RepositoryTest {

    public static final String TITLE_1 = "Test1";
    public static final String TITLE_2 = "Test2";

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void findAllBookSByNameTest() {
        // given
        var book1 = addBook(TITLE_1);
        var book2 = addBook(TITLE_2);

        // when
        Book result = bookRepository.findDistinctByNameCustom2(TITLE_1);

        // then
        assertEquals(book1.getId(), result.getId());
    }

    private Book addBook(String name) {
        var book = Book.builder()
                       .name(name)
                       .rating(10.0)
                       .build();
        return bookRepository.save(book);
    }
}
