package com.bookstore.web.orders;

import com.bookstore.service.BookService;
import com.bookstore.service.CartlistService;
import com.bookstore.service.OrdersService;
import com.bookstore.service.UsersService;
import com.bookstore.session.UsersInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value = "Order", description = "\n" + "Order management", tags = { "Order" })
@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrdersApiController {
    private final CartlistService cartlistService;
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final UsersInfo usersInfo;
    private final BookService bookService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @PostMapping("/addOrder")
    @ResponseBody
    public void addOrder(
        @RequestParam(value = "bookUid[]") List<Long> bookUid,
        @RequestParam(value = "count[]") List<Long> count,
        @RequestParam(value = "cardid") String cardid,
        @RequestParam(value = "addrUid") Long addrUid) {

        ordersService.createOrders(bookUid, count, cardid, addrUid);

        ordersService.addOrderslist(bookUid, count);

        bookService.updateCountBook(bookUid, count);

    }

    @PostMapping("/cartOrder")
    @ResponseBody
    public void cartlistOrder(
        @RequestParam(value = "bookUid[]") List<Long> bookUid,
        @RequestParam(value = "count[]") List<Long> count,
        @RequestParam(value = "cardid") String cardid,
        @RequestParam(value = "addrUid") Long addrUid
    ){

        ordersService.createOrders(bookUid, count, cardid, addrUid);

        ordersService.addOrderslist(bookUid, count);

        bookService.updateCountBook(bookUid, count);

        cartlistService.deleteCart();

    }
}
