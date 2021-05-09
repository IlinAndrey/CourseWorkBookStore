package com.bookstore.web.cartlist;

import com.bookstore.service.CartlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CartlistController {
    private final CartlistService cartlistService;


}
