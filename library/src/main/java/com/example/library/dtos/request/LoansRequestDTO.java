package com.example.library.dtos.request;

import com.example.library.models.BookCopy;

import java.util.Date;

public class LoansRequestDTO {
    private Long id;
    private BookCopy bookCopy;
    private Date loanDate;
    private Date loanRepaymentDate;

    public LoansRequestDTO(Long id, BookCopy bookCopy, Date loanDate, Date loanRepaymentDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.loanDate = loanDate;
        this.loanRepaymentDate = loanRepaymentDate;
    }

    public LoansRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
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
}
