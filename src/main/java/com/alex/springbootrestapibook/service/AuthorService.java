package com.alex.springbootrestapibook.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.alex.springbootrestapibook.repository.*;
import com.alex.springbootrestapibook.entity.Author;
import com.alex.springbootrestapibook.entity.Book;
import com.alex.springbootrestapibook.model.*;

@Service
public class AuthorService {
    private final AuthorRepo authorRepository;
    private final BookRepo bookRepository;
    private final ModelMapper mapper;

    public AuthorService(AuthorRepo authorRepository, BookRepo bookRepository, ModelMapper mapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<AuthorModel> getAllAuthors() {
        return authorRepository.findAll().stream().map(this::convertAuthorToModel).collect(Collectors.toList());
    }

                
                
    public AuthorWithBook getAuthorByIdWithBook(Long id) {
                
        AuthorWithBook author = mapper.map(authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found.")), AuthorWithBook.class);
        List<BookModel> books = bookRepository.findByAuthorId(id).stream().map(this::BookToBookModel).collect(Collectors.toList());
        author.setBookList(books);
        return author;
    }

    public AuthorModel saveAuthor(AuthorModel authorModel) {
        Author author = mapper.map(authorModel, Author.class);
        return mapper.map(authorRepository.save(author), AuthorModel.class);
    }

    private BookModel BookToBookModel(Book book) {
        return mapper.map(book, BookModel.class);
    }

    private AuthorModel convertAuthorToModel(Author author) {
        return mapper.map(author, AuthorModel.class);
    }
}
