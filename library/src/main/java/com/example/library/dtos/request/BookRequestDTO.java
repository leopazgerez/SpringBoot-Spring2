package com.example.library.dtos.request;

import com.example.library.models.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;

public class BookRequestDTO {
    private Long id;
    private Long code;
    private String title;
    private String ISBN;
    private String editorial;
    private Author author;
    @Min(0L)
    private Long amountCopies;

    public BookRequestDTO(Long id, Long code, String title, String isbn, String editorial, Author author, Long amountCopies) {
        this.id = id;
        this.code = code;
        this.title = title;
        ISBN = isbn;
        this.editorial = editorial;
        this.author = author;
        this.amountCopies = amountCopies;
    }

    public BookRequestDTO() {
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getAmountCopies() {
        return amountCopies;
    }

    public void setAmountCopies(Long amountCopies) {
        this.amountCopies = amountCopies;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
