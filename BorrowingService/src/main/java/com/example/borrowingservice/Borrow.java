package com.example.borrowingservice;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long bookId;
    private String userId; // Assume a user is identified by a string ID or username
    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Constructors
    public Borrow() {
    }

    public Borrow(Long bookId, String userId, LocalDate borrowDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
