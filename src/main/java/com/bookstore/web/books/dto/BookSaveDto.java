package com.bookstore.web.books.dto;

import com.bookstore.domain.book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookSaveDto {

    private String bookName;

    private Long bookCount;

    private Long bookPrice;

    private String bookDetail;

    private String bookPublish;

    private String bookAuthor;

    @Builder
    public BookSaveDto(String bookName, String bookAuthor, String bookPublish, String bookDetail, Long bookCount, Long bookPrice){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublish = bookPublish;
        this.bookDetail = bookDetail;
        this.bookCount = bookCount;
        this.bookPrice = bookPrice;
    }

    public Book toEntity(){
        return Book.builder().bookName(bookName).bookCount(bookCount).bookPrice(bookPrice).bookDetail(bookDetail).bookPublish(bookPublish).bookAuthor(bookAuthor).build();
    }
}
