package com.bookstore.service;

import com.bookstore.config.PasswordEncoding;
import com.bookstore.domain.address.Address;
import com.bookstore.domain.address.AddressRepository;
import com.bookstore.domain.card.Card;
import com.bookstore.domain.card.CardRepository;
import com.bookstore.domain.cart.CartRepository;
import com.bookstore.domain.user.Users;
import com.bookstore.domain.user.UsersRepository;
import com.bookstore.session.UsersInfo;
import com.bookstore.web.user.dto.AddrInfoDto;
import com.bookstore.web.user.dto.CardInfoDto;
import com.bookstore.web.user.dto.UsersSignInDto;
import com.bookstore.web.user.dto.UsersSignUpDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//сервис класса пользователь
@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final CardRepository cardRepository;
    private final AddressRepository addressRepository;

    PasswordEncoding passwordEncoding = new PasswordEncoding();

    @Transactional(readOnly = true)
    public Users findUsers(UsersInfo usersInfo){
        return usersRepository.findById(usersInfo.getUserId()).get();
    }

    @Transactional(readOnly = true)
    public boolean findUsersById(String id) {
        if(usersRepository.findById(id).isEmpty()){
            return false;
        }else {
            return true;
        }

    }

    @Transactional
    public String signup(UsersSignUpDto usersSignUpDto){
        usersSignUpDto.setPw(passwordEncoding.encode(usersSignUpDto.getPw()));
        return usersRepository.save(usersSignUpDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public boolean signin(UsersSignInDto usersSignInDto){
        String dbResultPw = usersRepository.getOne(usersSignInDto.getId()).getPw();
        String bodyResultPw = usersSignInDto.getPw();
        return passwordEncoding.matches(bodyResultPw, dbResultPw);
    }

    @Transactional(readOnly = true)
    public List<Card> findAllCard(UsersInfo usersInfo){
        return cardRepository.findAllByUsers_Id(usersInfo.getUserId());
    }

    @Transactional(readOnly = true)
    public Card findCardByCardId(String cardId){
        return cardRepository.findById(cardId).get();
    }

    @Transactional
    public String addCard(CardInfoDto cardInfoDto){
        return cardRepository.save(cardInfoDto.toEntity()).getId();
    }

    @Transactional
    public void deleteCard(String cardId) {
        cardRepository.delete(cardRepository.findById(cardId).get());
    }

    @Transactional(readOnly = true)
    public List<Address> findAllAddr(UsersInfo usersInfo){
        return addressRepository.findAllByUsers_Id(usersInfo.getUserId());
    }

    @Transactional(readOnly = true)
    public Address findAddrByUid(Long addrUid){
        return addressRepository.findById(addrUid).get();
    }

    @Transactional
    public Long addAddr(AddrInfoDto addrInfoDto){
        return addressRepository.save(addrInfoDto.toEntity()).getUid();
    }

    @Transactional
    public void deleteAddr(Long addrUid){
        addressRepository.delete(addressRepository.findById(addrUid).get());
    }

}
