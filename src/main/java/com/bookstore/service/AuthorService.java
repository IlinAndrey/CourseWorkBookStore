package com.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.bookstore.dto.AuthorToBookRequest;
import com.bookstore.Models.Book;
import com.bookstore.Models.Author;
import com.bookstore.repositories.BookRepository;
import com.bookstore.repositories.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void publish(AuthorToBookRequest request) {
        String bookName = request.getBookName();
        Book book = bookRepository.findByBookName(bookName);
        if (book == null) {
            return;
        }
        Author author = new Author();
        author.setFirstName(request.getAuthorFirstName());
        author.setLastName(request.getAuthorLastName());
        author.setMiddleName(request.getAuthorMiddleName());
        author.setBook(book);
        authorRepository.save(author);

        log.info("*****Criteria API*****");
        List<Author> authorsByBookName = authorRepository.findAuthorsByBookName("Buratino");
        log.info("-Authors by book name:");
        authorsByBookName
                .forEach(st ->
                        log.info(st.getLastName() + " " + st.getFirstName() + " " + st.getMiddleName() + " -- " + st.getBook().getBookName())
                );

        List<Author> authorsByFirstAndLastName = authorRepository.findAuthorsByFirstNameAndLastName("Alexey", "Tolstoy");
        log.info("-Authors by first and last name:");
        authorsByFirstAndLastName
                .forEach(st ->
                        log.info(st.getLastName() + " " + st.getFirstName() + " " + st.getMiddleName() + " -- " + st.getBook().getBookName())
                );
    }

    @Transactional
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void delete(long id) {
        authorRepository.deleteById(id);
    }
}
