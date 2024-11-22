package org.example.catalog.web;

import org.example.catalog.Book;
import org.example.catalog.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testSearchBooks_WithKeywords() throws Exception {
        List<Book> books = List.of(new Book("1", "Book One", "Description One", "Author One"));
        when(bookRepository.searchByKeywords("One")).thenReturn(books);

        mockMvc.perform(get("/api/books").param("keywords", "One"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[0].author").value("Author One"));
    }

}

