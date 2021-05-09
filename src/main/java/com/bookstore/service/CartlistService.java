package com.bookstore.service;

import com.bookstore.domain.book.BookRepository;
import com.bookstore.domain.cart.Cart;
import com.bookstore.domain.cart.CartRepository;
import com.bookstore.domain.cartlist.Cartlist;
import com.bookstore.domain.cartlist.CartlistRepository;
import com.bookstore.domain.cartlist.MultiId;
import com.bookstore.session.UsersInfo;
import com.bookstore.web.cartlist.dto.CartCreateDto;
import com.bookstore.web.cartlist.dto.CartlistAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartlistService {
    private final UsersInfo usersInfo;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartlistRepository cartlistRepository;
    private final UsersService usersService;
    private final BookService bookService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @Transactional(readOnly = true)
    public Cart cartfindByUser(){
        return cartRepository.findByUsers_Id(usersInfo.getUserId());
    }

    @Transactional(readOnly = true)
    public boolean existCart(){
        if(cartRepository.findByUsers_Id(usersInfo.getUserId()) != null){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public String createCart(){
        CartCreateDto cartCreateDto = new CartCreateDto();
        cartCreateDto.setCreatetime(nowDate);
        cartCreateDto.setModifytime(nowDate);
        cartCreateDto.setUsers(usersService.findUsers(usersInfo));

        return cartRepository.save(cartCreateDto.toEntity()).toString();
    }

    @Transactional
    public void deleteCart(){
        Cart cart = cartfindByUser();
        cartRepository.delete(cart);
    }

    @Transactional
    public String addCartlist(Long bookUid, CartlistAddDto cartlistAddDto){
        Long cartUid = cartRepository.findByUsers_Id(usersInfo.getUserId()).getUid();
        MultiId multiId = new MultiId();
        multiId.setCartUid(cartUid);
        multiId.setBookUid(bookUid);
        cartlistAddDto.setMultiId(multiId);
        cartlistAddDto.setBook(bookService.findBookById(bookUid));
        cartlistAddDto.setCart(cartfindByUser());

        return cartlistRepository.save(cartlistAddDto.toEntity()).toString();
    }

    @Transactional
    public void updateModifyTimeInCart(){
        Cart cart = cartfindByUser();
        cart.updateModifytime(nowDate);
    }

    @Transactional(readOnly = true)
    public List<Cartlist> findByCartuid(){
        Long cartUid = cartfindByUser().getUid();
        return cartlistRepository.findAllByCart_Uid(cartUid);
    }

    @Transactional
    public void deleteCartlist(Long bookUid){
        Cart cart = cartfindByUser();
        MultiId mId = new MultiId();
        mId.setCartUid(cart.getUid());
        mId.setBookUid(bookUid);
        cartlistRepository.deleteById(mId);
    }

}
