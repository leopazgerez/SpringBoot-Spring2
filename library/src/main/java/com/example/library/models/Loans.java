package com.example.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Loans {
    @Id
    @GeneratedValue
    private Long id;
    private BookCopy bookCopy;
    private User user;
    private Date loanDate;
    private Date loanRepaymentDate;

    public Long getId() {
        return id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
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
}
