package com.example.borrowingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrow")
    public ResponseEntity<Borrow> borrowBook(@RequestParam Long bookId, @RequestParam String userId) {
        try {
            Borrow borrowed = borrowService.borrowBook(bookId, userId);
            return ResponseEntity.ok(borrowed);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Consider a more informative response
        }
    }

    @PostMapping("/return/{borrowId}")
    public ResponseEntity<Borrow> returnBook(@PathVariable Long borrowId) {
        try {
            Borrow returned = borrowService.returnBook(borrowId);
            return ResponseEntity.ok(returned);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }
}
