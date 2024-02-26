package com.example.bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

//    @PutMapping("/{id}/status")
//    public Book updateBookStatus(@PathVariable Long id, @RequestBody String status) {
//        return bookService.updateBookStatus(id, status);
//    }

    @PutMapping("/{id}/status")
    public Book updateBookStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        return bookService.updateBookStatus(id, status);
    }



    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

