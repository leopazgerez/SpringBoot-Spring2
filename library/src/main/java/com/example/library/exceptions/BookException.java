package com.example.library.exceptions;

import com.example.library.models.Book;

import java.util.List;

public class BookException extends Exception {
    private final List<Book> booksAlreadyHas;
    private final List<Book> booksWithoutCopy;

    public BookException(List<Book> booksAlreadyHas, List<Book> booksWithoutCopy, String message) {
        super(message);
        this.booksAlreadyHas = booksAlreadyHas;
        this.booksWithoutCopy = booksWithoutCopy;
    }

    public List<Book> getBooksAlreadyHas() {
        return booksAlreadyHas;
    }

    public List<Book> getBooksWithoutCopy() {
        return booksWithoutCopy;
    }
}

