package com.bookstore.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, unique = true)
    private String bookName;
    @Column(nullable = false, length = 20, unique = true)
    private String bookPrice;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Author> authors;
}