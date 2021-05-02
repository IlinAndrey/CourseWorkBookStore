package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor
public class AuthorToBookRequest {
    @NotBlank
    private final String authorFirstName;
    @NotBlank
    private final String authorLastName;
    private final String authorMiddleName;
    @NotBlank
    private final String bookName;
    private final String bookPrice;
}
