package com.bookstore.dto;

import org.springframework.stereotype.Component;
import com.bookstore.Models.Book;
import com.bookstore.Models.Author;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public List<AuthorResponse> toAuthorResponseList(List<Author> authors) {
        return authors.stream().map(author -> {
            AuthorResponse response = new AuthorResponse();
            response.setId(author.getId());
            response.setFirstName(author.getFirstName());
            response.setLastName(author.getLastName());
            response.setMiddleName(author.getMiddleName());
            return response;
        }).collect(Collectors.toList());
    }

    public BookResponse toBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setBookName(book.getBookName());
        response.setBookPrice(book.getBookPrice());
        response.setAuthors(toAuthorResponseList(book.getAuthors()));
        return response;
    }

    public List<BookResponse> toBookResponseList(List<Book> books) {
        return books.stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }
}