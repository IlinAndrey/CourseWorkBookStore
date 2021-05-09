package com.bookstore.domain.user;

import com.bookstore.domain.address.Address;
import com.bookstore.domain.card.Card;
import com.bookstore.domain.cart.Cart;
import com.bookstore.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Users {

    @Id
    private String id;

    private String pw;

    private String name;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Card> card;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Address> address;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Cart> cart;

    @OneToMany(mappedBy = "users", orphanRemoval = true)
    private List<Orders> orders;

    @Builder
    public Users(String id, String pw, String name, List<Card> card, List<Address> address, List<Cart> cart, List<Orders> orders){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.card = card;
        this.address = address;
        this.cart = cart;
        this.orders = orders;
    }

}
