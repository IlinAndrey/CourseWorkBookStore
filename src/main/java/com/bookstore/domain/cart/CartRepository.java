package com.bookstore.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
//Метод класса корзина
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUsers_Id(String id);
}
