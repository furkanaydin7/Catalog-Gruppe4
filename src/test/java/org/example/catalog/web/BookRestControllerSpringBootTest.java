package org.example.catalog.web;

import org.example.catalog.Book;
import org.example.catalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerSpringBootTest {
    @LocalServerPort
    private int port;

    @Autowired
    private BookRepository bookRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        bookRepository.save(new Book("1", "Book One", "Description One", "Author One"));
        bookRepository.save(new Book("2", "Book Two", "Description Two", "Author Two"));
        System.out.println("Books in database: " + bookRepository.findAll().size());
    }

    @Test
    public void testSearchBooks_WithKeywords() {
        ResponseEntity<Book[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/books?keywords=One", Book[].class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
        assertEquals("Book One", response.getBody()[0].getTitle());
    }

}

