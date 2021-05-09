package com.bookstore.web.user;


import com.project.bookstore.config.PasswordEncoding;
import com.project.bookstore.domain.card.CardRepository;
import com.project.bookstore.domain.user.Users;
import com.project.bookstore.domain.user.UsersRepository;
import com.project.bookstore.session.UsersInfo;
import com.project.bookstore.web.user.dto.UsersSignInDto;
import com.project.bookstore.web.user.dto.UsersSignUpDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CardRepository cardRepository;

    UsersInfo usersinfo = new UsersInfo();
    
    PasswordEncoding passwordEncoding = new PasswordEncoding();

    @After
    public void tearDown() throws Exception{
        usersRepository.deleteById("test");
    }

    @Test
    public void signup() throws Exception{
        //given
        String id = "test";
        String pw = "test";
        String name = "test";

        UsersSignUpDto upDto = UsersSignUpDto.builder().id(id).pw(pw).name(name).build();

        String url = "http://localhost:"+ port + "/users/signup";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, upDto, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Users users = usersRepository.findById(id).get();
        assertThat(users.getId()).isEqualTo(id);
        assertThat(passwordEncoding.matches(pw, users.getPw())).isTrue();
        assertThat(users.getName()).isEqualTo(name);
    }

    @Test
    public void login() throws Exception{
        signup();
        String id = "test";
        String pw = "test";

        UsersSignInDto uSignInDto = UsersSignInDto.builder()
            .id(id)
            .pw(pw)
            .build();
        
        String url = "http://localhost:" + port + "/users/login";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, uSignInDto, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
