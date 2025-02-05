package com.example.library.services;

import com.example.library.models.Book;

public interface BookService {
    void createBook();

    Book getBook();

    Book updateBook();

    void deleteBook();
}
