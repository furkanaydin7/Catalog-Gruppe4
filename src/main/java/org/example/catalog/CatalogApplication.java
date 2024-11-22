package org.example.catalog;

import jakarta.annotation.PostConstruct;
import org.example.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

    private final BookRepository bookRepository;

    public CatalogApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }

    @PostConstruct
    public void createTestData() {
        bookRepository.save(new Book("978-3-16-148410-0", "Spring Boot in Action", "A comprehensive guide to Spring Boot.", "Craig Walls"));
        bookRepository.save(new Book("978-1-491-92435-0", "Learning Spring Boot", "Simplify your development with Spring Boot.", "Greg L. Turnquist"));
        bookRepository.save(new Book("978-0-596-52068-7", "Java Programming", "An introduction to programming in Java.", "Kathy Sierra"));
    }

}
