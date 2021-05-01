package com.bookstore.repositories;

import com.bookstore.Models.Author;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AuthorFilter {
    List<Author> findAuthorsByBookName(String bookName);
    List<Author> findAuthorsByFirstNameAndLastName(String fName, String lName);
}
