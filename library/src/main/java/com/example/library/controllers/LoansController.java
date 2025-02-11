package com.example.library.controllers;

import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.exceptions.BookException;
import com.example.library.models.Book;
import com.example.library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/loans")
public class LoansController {
    @Autowired
    LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<Set<LoanResponseDTO>> getAllLoans() {
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }

    @PostMapping("/create/{bookId}")
    public ResponseEntity<LoanResponseDTO> createLoan(@PathVariable Long bookId) {
        return new ResponseEntity<>(loanService.createLoan(bookId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Set<LoanResponseDTO>> createLoanMultipleBook(@RequestBody List<Book> books) throws BookException {
        return new ResponseEntity<>(loanService.createLoanMultipleBook(books), HttpStatus.OK);
    }
}
