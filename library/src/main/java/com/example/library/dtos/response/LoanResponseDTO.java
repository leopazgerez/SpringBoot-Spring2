package com.example.library.dtos.response;

import com.example.library.models.BookCopy;
import com.example.library.models.Loans;
import com.example.library.models.User;

import java.util.Date;

public class LoanResponseDTO {
    private Long id;
    private User user;
    private Date loanDate;
    private Date loanRepaymentDate;
    private BookCopy bookCopy;

    public LoanResponseDTO(Loans loan) {
        this.id = loan.getId();
        this.user = loan.getUser();
        this.loanDate = loan.getLoanDate();
        this.loanRepaymentDate = loan.getLoanRepaymentDate();
        this.bookCopy = loan.getBookCopy();
    }

    public LoanResponseDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanRepaymentDate() {
        return loanRepaymentDate;
    }

    public void setLoanRepaymentDate(Date loanRepaymentDate) {
        this.loanRepaymentDate = loanRepaymentDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
