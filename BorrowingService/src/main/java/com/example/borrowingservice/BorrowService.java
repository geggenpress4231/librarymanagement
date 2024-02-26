package com.example.borrowingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;


    @Autowired
    private BookServiceClient bookServiceClient;



    public Borrow returnBook(Long borrowId) {
        // In a real implementation, call the Book Service to update the book's status to available
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));
        borrow.setReturnDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow borrowBook(Long bookId, String userId) {
        // Optional: Check if the book is available first

        // Update the book's status to BORROWED
        bookServiceClient.updateBookStatus(bookId, Map.of("status", "BORROWED"));

        // Proceed with creating the borrow record
        Borrow newBorrow = new Borrow(bookId, userId, LocalDate.now());
        return borrowRepository.save(newBorrow);
    }

}
