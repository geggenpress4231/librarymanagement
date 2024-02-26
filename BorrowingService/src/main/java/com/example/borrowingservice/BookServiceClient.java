package com.example.borrowingservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "book-service")
public interface BookServiceClient {

    @GetMapping("/books/{id}")
    Map<String, Object> getBookById(@PathVariable("id") Long bookId);


    @PutMapping("/books/{id}/status")
    void updateBookStatus(@PathVariable("id") Long bookId, @RequestBody Map<String, String> statusUpdate);
}


