package com.bookstore.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Метод класса книга
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookNameIgnore(String name, String Author, String Publish);
}
