package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.calculator.request.BookLoanRequest;
import com.group.libraryapp.dto.calculator.request.BookReturnRequest;
import com.group.libraryapp.dto.user.request.BookCreateRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequest request){
        bookService.save(request);
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request){
        int i;
        bookService.loanBook(request);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request){
        bookService.returnBook(request);
    }

}
