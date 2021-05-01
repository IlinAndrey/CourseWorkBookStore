package com.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.bookstore.dto.AuthorToBookRequest;
import com.bookstore.service.BookService;
import com.bookstore.service.AuthorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final BookService bookService;
    private final AuthorService authorService;

    @PostMapping("add")
    public RedirectView add(
            @Valid @ModelAttribute("addAuthor") AuthorToBookRequest authorToBookRequest,
            BindingResult result
    ) {
        if (!result.hasErrors()) {
            bookService.publish(authorToBookRequest);
            authorService.publish(authorToBookRequest);
        }
        return new RedirectView("/home");
    }

    @PostMapping("{authorId}/delete")
    public RedirectView delete(@PathVariable long authorId) {
        authorService.delete(authorId);
        return new RedirectView("/home");
    }
}
