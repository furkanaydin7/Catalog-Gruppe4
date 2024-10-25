package org.example.catalog.repository;

import org.example.catalog.web.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
