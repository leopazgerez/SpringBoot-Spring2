package com.example.library.services;

import com.example.library.dtos.response.BookResponseDTO;
import com.example.library.models.Book;

import java.util.Set;

public interface BookService {
    void createBook();

    Set<BookResponseDTO> getAllBooks();

    Book getBook();

    Book updateBook();

    void deleteBook();
}
