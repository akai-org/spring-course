package pl.org.akai.springdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SimpleTest {

    @Mock
    private BookRepository bookRepository;

    public static final String TITLE_1 = "Test1";

    @BeforeEach
    void setUp() {

    }

    @Test
    void simpleMapperTest() {
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
