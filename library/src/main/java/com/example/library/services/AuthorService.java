package com.example.library.services;
import com.example.library.models.Author;

public interface AuthorService {
    void createAuthor();

    Author getAuthor();

    Author updateAuthor();

    void deleteAuthor();
}
