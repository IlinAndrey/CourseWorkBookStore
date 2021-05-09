package com.bookstore.domain.address;

import com.bookstore.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUsers_Id(String userid);
}
