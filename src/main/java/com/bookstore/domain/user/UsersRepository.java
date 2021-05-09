package com.bookstore.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
//Метод класса пользователь
public interface UsersRepository extends JpaRepository<Users, String> {
}
