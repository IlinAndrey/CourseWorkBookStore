package com.bookstore.domain.orderlist;

import org.springframework.data.jpa.repository.JpaRepository;
//Метод класса список заказов
public interface OrderlistRepository extends JpaRepository<Orderlist, OrderlistMultiid> {

}
