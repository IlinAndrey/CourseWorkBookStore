package com.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.dto.AuthorToBookRequest;
import com.bookstore.Models.Book;
import com.bookstore.repositories.BookRepository;
import com.bookstore.repositories.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void publish(AuthorToBookRequest request) {
        String bookName = request.getBookName();
        String bookPrice = request.getBookPrice();
        Book book = bookRepository.findByBookName(bookName);
        if (book != null) {
            return;
        }
        book = new Book();
        book.setBookName(request.getBookName());
        book.setBookPrice(request.getBookPrice());
        bookRepository.save(book);
    }

    @Transactional
    public <T> T takeAllBooks(Function<List<Book>, T> toDto) {
        List<Book> books = bookRepository.findAll();
        return toDto.apply(books);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public <T> T takeBookById(long id, Function<Book, T> toDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        return toDto.apply(book.get());
    }


    @Transactional
    public void delete(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return;
        }
        authorRepository.deleteAllByBook(book.get());
        bookRepository.deleteById(id);
    }
}
