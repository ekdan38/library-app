package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.userloanhistory.UserLoanHistory;
import com.group.libraryapp.dto.calculator.request.BookLoanRequest;
import com.group.libraryapp.dto.calculator.request.BookReturnRequest;
import com.group.libraryapp.dto.user.request.BookCreateRequest;
import com.group.libraryapp.repository.UserLoanHistoryRepository;
import com.group.libraryapp.repository.book.BookRepository;
import com.sun.xml.txw2.IllegalSignatureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    public void save(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    public void loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        boolean b = userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false);
        if(b){
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다.");
        }

        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName(), false));

    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        history.doReturn();

    }
}
