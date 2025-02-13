package com.example.library.controllers;

import com.example.library.dtos.request.BookRequestDTO;
import com.example.library.dtos.response.BookResponseDTO;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntity<>(bookService.createBook(bookRequestDTO), HttpStatus.OK);
    }
}
