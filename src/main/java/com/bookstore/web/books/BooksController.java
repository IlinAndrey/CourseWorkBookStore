package com.bookstore.web.books;

import com.bookstore.service.BookService;
import com.bookstore.session.UsersInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BooksController {
    private final BookService bookService;
    private final UsersInfo usersInfo;

    @GetMapping("/books/booksave")
    public String booksave(Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        return "books/saveBook";
    }

    @GetMapping("/books/bookdetail")
    public String bookdetail(@RequestParam(value = "uid") Long uid, Model model) {
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("bookInfo",bookService.findBookById(uid));
        if(usersInfo.getUserId() != null){
            if(usersInfo.getUserId().equals("master")){
                model.addAttribute("master", usersInfo.getUserId());
            }
        }
        if(usersInfo.getUserId() == null){
            model.addAttribute("existSession", "notExistSession");
        }
        return "books/detailBook";
    }

    @GetMapping("/books/bookupdate")
    public String bookupdate(@RequestParam(value="uid") Long uid, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("bookInfo", bookService.findBookById(uid));
        return "books/updateBook";
    }

    @GetMapping("/books/bookSearch")
    public String booksearch(@RequestParam(value = "sn") String search, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("bookInfo",bookService.findBookByLike(search));
        return "books/searchBook";
    }
}
