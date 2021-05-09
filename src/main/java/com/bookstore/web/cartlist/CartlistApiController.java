package com.bookstore.web.cartlist;

import com.bookstore.config.ApiResponse;
import com.bookstore.service.BookService;
import com.bookstore.service.CartlistService;
import com.bookstore.web.cartlist.dto.CartlistAddDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Cart", description = "Cart management", tags = { "Cart" })
@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartlistApiController {
    private final CartlistService cartlistService;
    private final BookService bookService;

    @ApiOperation(value = "Add to Shopping Cart")
    @PostMapping("/addCartlist/{bookUid}")
    public ResponseEntity<?> addCartlist(@PathVariable(value = "bookUid") Long bookUid, @RequestBody CartlistAddDto cartlistAddDto) {
        ApiResponse result = null;
        try {
            if (cartlistService.existCart() == true){
                System.out.println("Exist Cart");
                result = new ApiResponse(true, "success", cartlistService.addCartlist(bookUid, cartlistAddDto));
                cartlistService.updateModifyTimeInCart();
                return ResponseEntity.ok().body(result);
            }else {
                System.out.println("Not Extist Cart");
                cartlistService.createCart();
                result = new ApiResponse(true, "\n" + "success", cartlistService.addCartlist(bookUid, cartlistAddDto));
                return ResponseEntity.ok().body(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping(value = "/deleteCartlist")
    public void deleteCartlist(@RequestParam(value = "checkArr[]") List<Long> valueArr){
        for (Long string : valueArr) {
            cartlistService.deleteCartlist(string);
        }
    }



}
