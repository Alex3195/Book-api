package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.repository.AuthorRepo;
import com.alex.springbootrestapibook.repository.BookRepo;
import com.alex.springbootrestapibook.repository.CommentRepo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alex.springbootrestapibook.entity.Book;
import com.alex.springbootrestapibook.entity.Comment;
import com.alex.springbootrestapibook.model.BookModel;
import com.alex.springbootrestapibook.model.BookWithAuthor;
import com.alex.springbootrestapibook.model.BookWithAuthorId;
import com.alex.springbootrestapibook.model.BookWithComment;
import com.alex.springbootrestapibook.model.CommentModel;
import com.alex.springbootrestapibook.model.Pageable;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper mapper;

    public BookService(BookRepo bookRepository, AuthorRepo authorRepository, CommentRepo commentRepository,
            ModelMapper mapper) {
        this.bookRepo = bookRepository;
        this.authorRepo = authorRepository;
        this.commentRepo = commentRepository;
        this.mapper = mapper;
    }

    public Pageable<BookWithAuthorId> getAllBook(Long page) {
        page = page < 0 ? 1 : page;
        List<Book> books = bookRepo.findBookWithLimit(10L, (page - 1) * 10);
        long totalBooks = bookRepo.count();
        List<BookWithAuthorId> resBooks = books.stream().map(this::convertToBookModel).collect(Collectors.toList());
        return new Pageable<>(resBooks, page, totalBooks % 10 == 0 ? totalBooks / 10 : (totalBooks / 10 + 1));
    }

    public BookModel getBookById(Long id) {
        return bookRepo.getBookWithAuthor(id);
    }

    public BookWithComment getBookByIdWithComment(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        BookWithComment bookWithComment = mapper.map(book, BookWithComment.class);
        List<Comment> bookComments = commentRepo.findAllByBookId(id);
        bookWithComment
                .setCommentList(bookComments.stream().map(this::commentTOCommentModel).collect(Collectors.toList()));
        return bookWithComment;
    }

    public List<BookWithAuthor> filterBookByAuthorOrISBN10(String isbn_10, String author) {
        if (StringUtils.hasText(isbn_10) && StringUtils.hasText(author))
            return bookRepo.findByAuthorNameAndISBN_10(author, isbn_10);
        if (StringUtils.hasText(author))
            return bookRepo.findByAuthorName(author);
        if (StringUtils.hasText(isbn_10))
            return bookRepo.findByISBN_10(isbn_10);

        return new ArrayList<>();
    }

    public BookWithAuthorId saveBook(BookWithAuthorId book) throws NotFoundException {
        if (!authorRepo.existsById(book.getAuthorId())) {
            throw new NotFoundException("Author not found");
        }
        Book bookEntity = mapper.map(book, Book.class);
        bookRepo.save(bookEntity);
        return mapper.map(bookEntity, BookWithAuthorId.class);
    }

    private BookWithAuthorId convertToBookModel(Book book) {
        return mapper.map(book, BookWithAuthorId.class);
    }

    private CommentModel commentTOCommentModel(Comment comment) {
        return mapper.map(comment, CommentModel.class);
    }

}
