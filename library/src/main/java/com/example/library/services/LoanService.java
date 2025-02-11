package com.example.library.services;

import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.exceptions.BookException;
import com.example.library.models.Book;
import com.example.library.models.Loans;

import java.util.List;
import java.util.Set;

public interface LoanService {
    LoanResponseDTO createLoan(Long bookCopyId);

    Set<LoanResponseDTO> createLoanMultipleBook(List<Book> books) throws BookException;

    Loans getLoanById();

    Set<LoanResponseDTO> getAllLoans();

    Loans updateLoan();

    void deleteLoan();
}
