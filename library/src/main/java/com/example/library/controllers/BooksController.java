package com.example.library.controllers;

import com.example.library.dtos.response.BookResponseDTO;
import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/books")
public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Set<BookResponseDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
}
