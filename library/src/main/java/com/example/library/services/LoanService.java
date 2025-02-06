package com.example.library.services;

import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.models.Loans;

import java.util.Set;

public interface LoanService {
    LoanResponseDTO createLoan(Long bookCopyId);

    Loans getLoan();

    Set<LoanResponseDTO> getAllLoans();

    Loans updateLoan();

    void deleteLoan();
}
