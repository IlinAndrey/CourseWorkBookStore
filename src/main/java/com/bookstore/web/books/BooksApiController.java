package com.bookstore.web.books;

import com.bookstore.config.ApiResponse;
import com.bookstore.service.BookService;
import com.bookstore.web.books.dto.BookSaveDto;
import com.bookstore.web.books.dto.BookUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "books", description = "\n" +
        "Book management", tags = { "\n" +
        "books" })
@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BooksApiController {
    private final BookService bookService;

    @ApiOperation(value = "Book registration")
    @PostMapping("/saveBooks")
    public ResponseEntity<?> saveBooks(@RequestBody BookSaveDto bookSaveDto){
        ApiResponse result = null;
        try {
            result = new ApiResponse(true, "\n" + "success", bookService.saveBook(bookSaveDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Book modification")
    @PostMapping("/updateBooks/{uid}")
    public ResponseEntity<?> updateBooks(@PathVariable("uid") Long uid, @RequestBody BookUpdateDto bookUpdateDto){
        ApiResponse result = null;
        bookUpdateDto.setUid(uid);
        try {
            bookService.updateBook(uid, bookUpdateDto);
            result = new ApiResponse(true, "\n" + "success",bookService.findBookById(uid));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Delete book")
    @PostMapping("/deleteBooks/{uid}")
    public RedirectView deleteBooks(@PathVariable("uid") Long uid){
        bookService.deleteBook(uid);
        return new RedirectView("/");
    }

}
