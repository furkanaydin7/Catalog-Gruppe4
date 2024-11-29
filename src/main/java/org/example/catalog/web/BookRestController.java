package org.example.catalog.web;

import org.example.catalog.Book;
import org.example.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> searchBooks(@RequestParam(required = false) String keywords) {
        if (keywords == null || keywords.isBlank()) {
            return bookRepository.findAll(); // Alle Bücher zurückgeben
        }
        return bookRepository.searchByKeywords(keywords);
    }

    @GetMapping("/{isbn}")
    public Optional<Book> getBookById(@PathVariable String isbn) {
        return bookRepository.findById(isbn);
    }
}

