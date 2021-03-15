package com.alex.springbootrestapibook.controller;

import java.util.List;

import com.alex.springbootrestapibook.model.BookModel;
import com.alex.springbootrestapibook.model.BookWithAuthor;
import com.alex.springbootrestapibook.model.BookWithAuthorId;
import com.alex.springbootrestapibook.model.BookWithComment;
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
    BookService bookService;

    @GetMapping()
    private ResponseEntity<Pageable<BookWithAuthorId>> getAllBooks(@RequestParam(name = "page", defaultValue = "1") Long page) {
        return ResponseEntity.ok(bookService.getAllBook(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookWithAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<BookWithComment> getBookWithComment(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookByIdWithComment(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookWithAuthor>> filterBook(@RequestParam(value = "ISBN_10", defaultValue = "") String ISBN_10, @RequestParam(value = "author", defaultValue = "") String author) {
        return ResponseEntity.ok(bookService.filterBookByAuthorOrISBN10(ISBN_10, author));
    }

    @PostMapping
    private ResponseEntity<BookWithAuthorId> saveBook(@RequestBody BookWithAuthorId book) throws NotFoundException {
        return ResponseEntity.ok(bookService.saveBook(book));
    }
}
