package com.alex.springbootrestapibook.controller;

import java.util.List;

import com.alex.springbootrestapibook.model.BookModel;
import com.alex.springbootrestapibook.model.BookWithAuthorModel;
import com.alex.springbootrestapibook.model.BookWithAuthorIdModel;
import com.alex.springbootrestapibook.model.BookWithCommentsModel;
import com.alex.springbootrestapibook.model.Pageable;

import com.alex.springbootrestapibook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    private ResponseEntity<List<BookWithAuthorIdModel>> getAllBooks(@RequestParam(name = "page") Long page) {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookWithAuthorIdModel>> getBookWithAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBooksByAuthorId(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<BookWithCommentsModel> getBookWithComment(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookByIdWithComments(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookWithAuthorModel>> filterBook(@RequestParam(value = "ISBN_10", defaultValue = "") String ISBN_10, @RequestParam(value = "author", defaultValue = "") String author) {
        return ResponseEntity.ok(bookService.filterBookByAuthorOrISBN10(ISBN_10, author));
    }

    @PostMapping
    private ResponseEntity<BookWithAuthorIdModel> saveBook(@RequestBody BookWithAuthorIdModel book) throws NotFoundException {
        return ResponseEntity.ok(bookService.saveBook(book));
    }
}
