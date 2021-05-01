package com.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bookstore.dto.DtoConverter;
import com.bookstore.service.BookService;

import java.util.Map;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;
    private final DtoConverter dtoConverter;

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put(
                "books",
                bookService.takeAllBooks(dtoConverter::toBookResponseList)
        );
        return "bucket";
    }
}
