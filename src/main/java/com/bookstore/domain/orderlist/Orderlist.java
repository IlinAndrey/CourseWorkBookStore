package com.bookstore.domain.orderlist;

import com.bookstore.domain.book.Book;
import com.bookstore.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class Orderlist implements Serializable {

    @EmbeddedId
    private OrderlistMultiid orderlistMultiid;

    private Long count;

    @MapsId("ordersUid")
    @ManyToOne
    @JoinColumn(name = "ORDERS_UID")
    private Orders orders;

    @MapsId("bookUid")
    @ManyToOne
    @JoinColumn(name = "BOOK_UID")
    private Book book;

    @Builder
    public Orderlist(OrderlistMultiid orderlistMultiid, Long count, Orders orders, Book book){
        this.orderlistMultiid = orderlistMultiid;
        this.count = count;
        this.orders = orders;
        this.book = book;
    }
}
