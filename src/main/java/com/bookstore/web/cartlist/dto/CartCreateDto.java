package com.bookstore.web.cartlist.dto;

import com.bookstore.domain.cart.Cart;
import com.bookstore.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartCreateDto {
    private Users users;
    private String createtime;
    private String modifytime;

    public Cart toEntity(){
        return Cart.builder()
                .users(users)
                .createtime(createtime)
                .modifytime(modifytime)
                .build();
    }
}
