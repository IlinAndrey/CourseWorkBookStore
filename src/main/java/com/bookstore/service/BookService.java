package com.bookstore.service;

import com.bookstore.domain.book.Book;
import com.bookstore.domain.book.BookRepository;
import com.bookstore.domain.cart.CartRepository;
import com.bookstore.session.UsersInfo;
import com.bookstore.web.books.dto.BookDeleteDto;
import com.bookstore.web.books.dto.BookSaveDto;
import com.bookstore.web.books.dto.BookUpdateCountDto;
import com.bookstore.web.books.dto.BookUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
//Сервис класса книга
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    private final UsersInfo usersInfo;

    @Transactional
    public String saveBook(BookSaveDto bookSaveDto){
        return bookRepository.save(bookSaveDto.toEntity()).toString();
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long bookUid){
        return bookRepository.findById(bookUid).get();
    }

    @Transactional
    public void  updateBook(Long bookUid, BookUpdateDto bookUpdateDto){
        findBookById(bookUid).updateBook(bookUpdateDto);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByLike(String name){
        return bookRepository.findAllByBookNameIgnore(name, name, name);
    }

    @Transactional
    public void updateCountBook(List<Long> bookUid, List<Long> count){
        int index = 0;
        for (Long bookuid : bookUid) {
            System.out.println("Book count update");
            Book book = new Book();
            book = findBookById(bookuid);
            Long updateBookCount = book.getBookCount() - count.get(index);
            BookUpdateCountDto bookUpdateCountDto = new BookUpdateCountDto();
            bookUpdateCountDto.setBookCount(updateBookCount);
            findBookById(bookuid).updateCount(bookUpdateCountDto);
            index++;
        }
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByArrayUid(List<Long> bookUid){
        List<Book> arrBook = new ArrayList<Book>();
        for (Long uid : bookUid) {
            arrBook.add(bookRepository.getOne(uid));
        }
        return arrBook;
    }


    @Transactional
    public void deleteBook(Long uid){
        bookRepository.delete(findBookById(uid));
    }
}

