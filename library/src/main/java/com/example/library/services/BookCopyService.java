package com.example.library.services;


import com.example.library.models.BookCopy;

public interface BookCopyService {
    void createBookCopy();

    BookCopy getBookCopy();

    BookCopy updateBookCopy();

    void deleteBookCopy();
}
