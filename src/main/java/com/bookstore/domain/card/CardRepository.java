package com.bookstore.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Метод класса карты
public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByUsers_Id(String userid);
}
