package pl.org.akai.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class MockBeanTest {

    public static final String TITLE_1 = "Test1";
    @MockBean
    BookRepository bookRepository;

    @Test
    void findAllBookSByNameTest() {
        // given
        var book = Book.builder()
                       .name(TITLE_1)
                       .rating(10.0)
                       .build();
        when(bookRepository.findDistinctByNameCustom2(anyString())).thenReturn(book);

        // when
        Book result = bookRepository.findDistinctByNameCustom2(TITLE_1);

        // then
        assertEquals(book.getId(), result.getId());
    }
}