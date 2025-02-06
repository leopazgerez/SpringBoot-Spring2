package com.example.library.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    @OneToOne()
    private Location location;
    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    //    En el mappedBy, va el nombre con el que se declaro la variable en la relacion con la clase
    @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Loans> loans = new HashSet<>();

    public BookCopy(Long code, Location location, Book book) {
        this.code = code;
        this.location = location;
        this.book = book;
    }
    public BookCopy() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<Loans> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loans> loans) {
        this.loans = loans;
    }

    public void addLoan(Loans loan) {
        loan.setBookCopy(this);
        loans.add(loan);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
