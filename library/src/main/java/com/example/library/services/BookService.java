package com.example.library.services;

import com.example.library.dtos.request.BookRequestDTO;
import com.example.library.dtos.response.BookResponseDTO;

import java.util.Set;

public interface BookService {
    Set<BookResponseDTO> getAllBooks();

    BookResponseDTO getBook(Long bookId);

    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO);

    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);

    void deleteBook(Long bookId);
}
