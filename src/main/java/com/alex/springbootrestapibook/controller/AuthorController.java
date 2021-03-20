package com.alex.springbootrestapibook.controller;

import java.util.List;

import com.alex.springbootrestapibook.model.AuthorModel;
import com.alex.springbootrestapibook.model.AuthorWithBookModel;

import com.alex.springbootrestapibook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorModel>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorWithBookModel> getAuthorWithBook(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorByIdWithBooks(id));
    }

    @PostMapping
    public ResponseEntity<AuthorModel> saveAuthor(@RequestBody AuthorModel author) {
        return ResponseEntity.ok(authorService.saveAuthor(author));
    }
}
