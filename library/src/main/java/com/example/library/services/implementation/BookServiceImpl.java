package com.example.library.services.implementation;

import com.example.library.dtos.request.BookRequestDTO;
import com.example.library.dtos.response.BookResponseDTO;
import com.example.library.models.Book;
import com.example.library.models.BookCopy;
import com.example.library.repository.BookCopiesRepository;
import com.example.library.repository.BooksRepository;
import com.example.library.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BooksRepository booksRepository;
    @Autowired
    BookCopiesRepository bookCopiesRepository;

    @Override
    public Set<BookResponseDTO> getAllBooks() {
        return booksRepository.findAll().stream().map(BookResponseDTO::new).collect(Collectors.toSet());
    }

    @Override
    public BookResponseDTO getBook(Long bookId) {
        return new BookResponseDTO(booksRepository.findById(bookId).orElseThrow());
    }

    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO) {
        Book bookFounded = booksRepository.findById(bookRequestDTO.getId()).orElseThrow();
        bookFounded.copyWith(bookRequestDTO);
        return new BookResponseDTO(booksRepository.save(bookFounded));
    }

    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book bookToSave = new Book(bookRequestDTO);
        Book bookSaved = null;
        List<BookCopy> copies = new ArrayList<>();
        if (bookRequestDTO.getAmountCopies() == 0) {
            copies.add(new BookCopy(bookToSave));
        } else {
            for (int index = 1; index <= bookRequestDTO.getAmountCopies(); index++) {
                copies.add(new BookCopy(bookToSave));
            }
        }
        try {
            bookSaved = booksRepository.save(bookToSave);
            bookCopiesRepository.saveAll(copies);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw exception;
        }
        return new BookResponseDTO(bookSaved);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        try {
            booksRepository.deleteById(bookId);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw exception;
        }
    }
}
