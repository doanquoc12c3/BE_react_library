package com.doanquoc.springbootlibrary.controller;


import com.doanquoc.springbootlibrary.entity.Book;
import com.doanquoc.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception{
        String userEmail = "test@gmail.com";
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId){
        String userEmail = "test@gmail.com";
        return bookService.checkoutBookByUser(userEmail, bookId);
    }
}
