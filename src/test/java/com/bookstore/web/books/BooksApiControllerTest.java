package com.bookstore.web.books;

import com.bookstore.domain.book.Book;
import com.bookstore.domain.book.BookRepository;
import com.bookstore.web.books.dto.BookSaveDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksApiControllerTest {

    private Sort sortByIdDesc() {
        return Sort.by(Sort.Direction.DESC, "uid");
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown() throws Exception{
        Book book = bookRepository.findAll(sortByIdDesc()).get(0);
        bookRepository.deleteById(book.getUid());
    }

    @Test
    public void addBook() throws Exception{
        //given
        String bookName = "test";
        String bookAuthor = "test";
        String bookPublish = "test";
        String bookDetail = "test";
        Long bookCount = (long) 1;
        Long bookPrice = (long) 1;

        BookSaveDto  bSaveDto = BookSaveDto.builder()
            .bookName(bookName)
            .bookAuthor(bookAuthor)
            .bookPublish(bookPublish)
            .bookDetail(bookDetail)
            .bookCount(bookCount)
            .bookPrice(bookPrice)
            .build();
        
        String url = "http://localhost:" + port + "/books/saveBooks";

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, bSaveDto, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Book book = bookRepository.findAll(sortByIdDesc()).get(0);
        assertThat(book.getBookName()).isEqualTo(bookName);
        assertThat(book.getBookAuthor()).isEqualTo(bookAuthor);
        assertThat(book.getBookPublish()).isEqualTo(bookPublish);
        assertThat(book.getBookDetail()).isEqualTo(bookDetail);
        assertThat(book.getBookCount()).isEqualTo(bookCount);
        assertThat(book.getBookPrice()).isEqualTo(bookPrice);

    }
    
}
