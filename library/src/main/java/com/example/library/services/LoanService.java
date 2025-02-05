package com.example.library.services;

import com.example.library.models.Loans;

public interface LoanService {
    void createLoan();

    Loans getLoan();

    Loans updateLoan();

    void deleteLoan();
}
