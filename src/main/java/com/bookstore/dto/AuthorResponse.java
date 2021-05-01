package com.bookstore.dto;

import com.sun.istack.NotNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@ToString
public class AuthorResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
}
