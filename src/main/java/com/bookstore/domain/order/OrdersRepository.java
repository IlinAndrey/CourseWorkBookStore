package com.bookstore.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Метод класса заказ
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByOrderByUidDesc();
    List<Orders> findAllByUsers_Id(String userId);
}
