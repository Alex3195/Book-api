package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Book;
import com.alex.springbootrestapibook.model.*;
import com.alex.springbootrestapibook.repository.AuthorRepo;
import com.alex.springbootrestapibook.repository.BookRepo;
import javassist.NotFoundException;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    private ModelMapper mapper;

    public List<BookWithAuthorIdModel> getAllBooks() {
        List<Book> bookList = bookRepo.findAll();
        List<BookWithAuthorIdModel> result = bookList.stream().map(this::convertBookToBookWithAuthorModel).collect(Collectors.toList());
        return result;
    }

    public List<BookWithAuthorIdModel> getBooksByAuthorId(Long authorId) {
        List<Book> books = bookRepo.findByAuthorId(authorId);
        List<BookWithAuthorIdModel> result = books.stream().map(this::convertBookToBookWithAuthorModel).collect(Collectors.toList());
        return result;
    }

    public List<BookModel> getBooksByCategorId(Long id) {
        List<Book> books = bookRepo.findByCategoryId(id);
        List<BookModel> result = books.stream().map(this::convertBookModel).collect(Collectors.toList());
        return result;
    }

    public List<BookModel> getBooksByName(String name) {
        List<Book> books = bookRepo.findByName(name);
        List<BookModel> result = books.stream().map(this::convertBookModel).collect(Collectors.toList());
        return result;
    }

    public List<BooksWithCategoryIdAndRateModel> getBooksByCategoryIdAndRate(Long categoryId, Double rate) {
        List<Book> books = bookRepo.findBookByCategoryIdAndRate(categoryId, rate);
        List<BooksWithCategoryIdAndRateModel> result = books.stream().map(this::convertBookToBooksWithCategoryIdAndRateModel).collect(Collectors.toList());
        return result;
    }

    private BookModel convertBookModel(Book book) {
        return mapper.map(book, BookModel.class);
    }

    private BookWithAuthorIdModel convertBookToBookWithAuthorModel(Book book) {
        return mapper.map(book, BookWithAuthorIdModel.class);
    }

    private BooksWithCategoryIdAndRateModel convertBookToBooksWithCategoryIdAndRateModel(Book book) {
        return mapper.map(book, BooksWithCategoryIdAndRateModel.class);
    }

    public BookWithCommentsModel getBookByIdWithComments(Long id) {
        return null;
    }

    public List<BookWithAuthorModel> filterBookByAuthorOrISBN10(String isbn_10, String author) {
        if (StringUtils.hasText(isbn_10) && StringUtils.hasText(author))
            return bookRepo.findAllByAuthorNameAndISBN_10(author, isbn_10);
        if (StringUtils.hasText(author))
            return bookRepo.findAllByAuthorName(author);
        if (StringUtils.hasText(isbn_10))
            return bookRepo.findAllByISBN_10(isbn_10);

        return new ArrayList<>();
    }

    public BookWithAuthorIdModel saveBook(BookWithAuthorIdModel book) throws NotFoundException {
        if (!authorRepo.existsById(book.getAuthorId()))
            throw  new NotFoundException("Author not found");
        Book bookEntity = mapper.map(book, Book.class);
        bookRepo.save(bookEntity);
        return mapper.map(bookEntity, BookWithAuthorIdModel.class);
    }
}
