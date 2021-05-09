package com.bookstore.web.orders;

import com.bookstore.service.BookService;
import com.bookstore.service.OrdersService;
import com.bookstore.service.UsersService;
import com.bookstore.session.UsersInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrdersController {
    private final UsersInfo usersInfo;
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final BookService bookservice;

    @GetMapping("/orders/addOrder")
    public String addOrder(@RequestParam(value = "bookUid") Long bookUid, @RequestParam(value="count") Long count ,Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("addrInfo", usersService.findAllAddr(usersInfo));
        model.addAttribute("bookInfo", bookservice.findBookById(bookUid));
        model.addAttribute("count", count);

        return "orders/addOrder";
    }

    @GetMapping("/orders/addCartlistOrder")
    public String addCartlistOrder(@RequestParam(value = "bookUid[]") List<Long> bookUid, @RequestParam(value = "count[]") Long[] count , Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("addrInfo", usersService.findAllAddr(usersInfo));
        model.addAttribute("bookInfo", bookservice.findBookByArrayUid(bookUid));
        model.addAttribute("count", count);

        return "orders/addCartlistOrder";
    }


}
