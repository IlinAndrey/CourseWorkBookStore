package com.bookstore.domain.cartlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Метод класса список корзины
public interface CartlistRepository extends JpaRepository<Cartlist, MultiId> {
    List<Cartlist> findAllByCart_Uid(Long uid);
}
