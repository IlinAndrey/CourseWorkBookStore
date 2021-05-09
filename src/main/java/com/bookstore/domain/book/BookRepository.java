package com.bookstore.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookNameIgnoreCaseContainingOrBookAuthorIgnoreCaseContainingOrBookPublishIgnoreCaseContaining(String name, String Author, String Publish);
}
