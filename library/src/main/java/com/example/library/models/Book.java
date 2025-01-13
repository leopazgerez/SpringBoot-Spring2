package com.example.library.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private String title;
    private Long ISBN;
    private String editorial;
    @OneToMany(mappedBy = "book")
    private Set<BookItem> bookItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Set<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(Set<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
