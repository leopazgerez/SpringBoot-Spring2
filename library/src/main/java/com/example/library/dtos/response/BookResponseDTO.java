package com.example.library.dtos.response;

import com.example.library.models.Author;
import com.example.library.models.Book;

public class BookResponseDTO {
    private Long id;
    private Long code;
    private String title;
    private String ISBN;
    private String editorial;
    private Author author;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.code = book.getCode();
        this.title = book.getTitle();
        this.ISBN = book.getISBN();
        this.editorial = book.getEditorial();
        this.author = book.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
