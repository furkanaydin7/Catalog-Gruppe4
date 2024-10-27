package org.example.catalog;

import org.example.catalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {

        bookRepository.deleteAll();

        Book book1 = new Book("123-456-789", "Spring Boot Guide", "A comprehensive guide to Spring Boot", "John Doe");
        Book book2 = new Book("456-789-123", "Java Basics", "Introduction to Java programming", "Jane Doe");
        Book book3 = new Book("789-123-456", "Hibernate in Action", "Master Hibernate ORM", "John Smith");

        bookRepository.saveAll(Arrays.asList(book1, book2, book3));  // Verwende saveAll() mit JpaRepository
    }

    @Test
    public void testSearchBooks() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("keywords", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring Boot Guide"));
    }

    @Test
    public void testSearchBooksWithMultipleKeywords() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("keywords", "Java Basics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Java Basics"));
    }
    @Test
    public void testSearchBooksReturnsJson() throws Exception {

        mockMvc.perform(get("/api/books/search")
                        .param("keywords", "Spring")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].isbn").value("123-456-789"))
                .andExpect(jsonPath("$[0].title").value("Spring Boot Guide"))
                .andExpect(jsonPath("$[0].author").value("John Doe"));
    }
}
