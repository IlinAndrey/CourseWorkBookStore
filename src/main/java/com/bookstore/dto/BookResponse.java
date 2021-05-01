package com.bookstore.dto;

import com.sun.istack.NotNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@ToString
public class BookResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String bookName;
    private List<AuthorResponse> authors;
}
