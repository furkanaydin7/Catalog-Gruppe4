package org.example.catalog.repository;

import org.example.catalog.Book;
import org.example.catalog.TestcontainersConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        // Add test data
        bookRepository.deleteAll();
        bookRepository.save(new Book("1", "Book One", "Description One", "Author One"));
        bookRepository.save(new Book("2", "Book Two", "Description Two", "Author Two"));
        bookRepository.save(new Book("3", "Book Three", "Description Three", "Author Three"));
    }

    @Test
    public void testSearchByKeywords_Title() {
        List<Book> books = bookRepository.searchByKeywords("One");

        assertEquals(1, books.size());
        assertEquals("Book One", books.get(0).getTitle());
    }

    @Test
    public void testSearchByKeywords_Author() {
        List<Book> books = bookRepository.searchByKeywords("Author Two");

        assertEquals(1, books.size());
        assertEquals("Book Two", books.get(0).getTitle());
    }

    @Test
    public void testSearchByKeywords_Description() {
        List<Book> books = bookRepository.searchByKeywords("Description Three");

        assertEquals(1, books.size());
        assertEquals("Book Three", books.get(0).getTitle());
    }

    @Test
    public void testSearchByKeywords_NoMatch() {
        List<Book> books = bookRepository.searchByKeywords("Nonexistent");

        assertTrue(books.isEmpty());
    }
}
