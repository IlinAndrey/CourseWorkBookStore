package com.bookstore.domain.book;

import com.bookstore.domain.cartlist.Cartlist;
import com.bookstore.domain.orderlist.Orderlist;
import com.bookstore.web.books.dto.BookUpdateCountDto;
import com.bookstore.web.books.dto.BookUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Book {

    //책번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_UID")
    private Long uid;

    private String bookName;

    private Long bookCount;

    private Long bookPrice;

    private String bookDetail;

    private String bookPublish;

    private String bookAuthor;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.REMOVE})
    private List<Cartlist> cartlists;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<Orderlist> orderlists;

    @Builder
    public Book(Long uid, String bookName, Long bookCount, Long bookPrice, String bookDetail, String bookPublish, String bookAuthor, List<Cartlist> cartlists, List<Orderlist> orderlists){
        this.uid = uid;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.bookPrice = bookPrice;
        this.bookDetail = bookDetail;
        this.bookPublish = bookPublish;
        this.bookAuthor = bookAuthor;
        this.cartlists = cartlists;
        this.orderlists = orderlists;
    }

    public void updateBook(BookUpdateDto bookUpdateDto){
        this.bookName = bookUpdateDto.getBookName();
        this.bookCount = bookUpdateDto.getBookCount();
        this.bookPrice = bookUpdateDto.getBookPrice();
        this.bookDetail = bookUpdateDto.getBookDetail();
        this.bookPublish = bookUpdateDto.getBookPublish();
        this.bookAuthor = bookUpdateDto.getBookAuthor();
    }

    public void updateCount(BookUpdateCountDto bookUpdateCountDto){
        this.bookCount = bookUpdateCountDto.getBookCount();
    }
}
