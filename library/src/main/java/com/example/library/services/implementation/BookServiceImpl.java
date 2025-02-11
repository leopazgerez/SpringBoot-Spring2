package com.example.library.services.implementation;

import com.example.library.dtos.response.BookResponseDTO;
import com.example.library.models.Book;
import com.example.library.repository.BooksRepository;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public void createBook() {

    }

    @Override
    public Set<BookResponseDTO> getAllBooks() {
        return booksRepository.findAll().stream().map(BookResponseDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public Book updateBook() {
        return null;
    }

    @Override
    public void deleteBook() {

    }
}
