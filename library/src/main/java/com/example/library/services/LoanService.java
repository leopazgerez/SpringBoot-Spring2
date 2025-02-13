package com.example.library.services;

import com.example.library.dtos.request.LoansRequestDTO;
import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.exceptions.BookException;
import com.example.library.models.Book;
import com.example.library.models.Loans;

import java.util.List;
import java.util.Set;

public interface LoanService {
    LoanResponseDTO createLoan(Long bookCopyId);

    Set<LoanResponseDTO> createLoanMultipleBook(List<Book> books) throws BookException;

    LoanResponseDTO getLoanById(Long loanId);

    Set<LoanResponseDTO> getAllLoans();

    LoanResponseDTO updateLoan(LoansRequestDTO loansRequestDTO);

    void deleteLoan(Long loanId);
}
