package com.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.bookstore.dto.DtoConverter;
import com.bookstore.service.BookService;

import java.util.Map;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final DtoConverter dtoConverter;
    private final BookService bookService;


    @GetMapping
    public String getBooks(Map<String, Object> model) {
        model.put(
                "books",
                bookService.takeAllBooks(dtoConverter::toBookResponseList)
        );
        return "book";
    }

    @PostMapping("{bookId}")
    public String getBook(
            @PathVariable long bookId,
            Map<String, Object> model) {
        model.put(
                "book",
                bookService.takeBookById(bookId, dtoConverter::toBookResponse)
        );
        return "book";
    }

    @PostMapping("buy/{bookId}")
    public String price(){
        return "buy/{bookId}";
    }


    @PostMapping("{bookId}/delete")
    public RedirectView delete(@PathVariable long bookId) {
        bookService.delete(bookId);
        return new RedirectView("/home");
    }


    @GetMapping("add")
    public String add() {
        return "add";
    }
}
