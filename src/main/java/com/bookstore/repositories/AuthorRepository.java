package com.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.Models.Book;
import com.bookstore.Models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorFilter {
    void deleteAllByBook(Book book);
}