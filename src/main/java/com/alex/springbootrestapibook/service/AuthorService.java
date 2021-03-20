package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Author;
import com.alex.springbootrestapibook.model.AuthorModel;
import com.alex.springbootrestapibook.model.AuthorWithBookModel;
import com.alex.springbootrestapibook.repository.AuthorRepo;
import javassist.NotFoundException;
import org.hibernate.annotations.NotFoundAction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    private ModelMapper mapper;

    public AuthorModel saveAuthor(AuthorModel authorModel) {
        Author author = mapper.map(authorModel, Author.class);
        AuthorModel res = mapper.map(authorRepo.save(author), AuthorModel.class);
        return res;
    }

    public List<AuthorModel> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        List<AuthorModel> res = authors.stream().map(this::convertToAuthorModel).collect(Collectors.toList());
        return res;
    }

    public AuthorModel getAuthorById(Long id) {
        Author author = authorRepo.findById(id).get();
        AuthorModel result = convertToAuthorModel(author);
        return result;
    }

    public AuthorWithBookModel getAuthorByIdWithBooks(Long id) {
        AuthorWithBookModel res = mapper.map(authorRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Author not found")), AuthorWithBookModel.class);
        return res;
    }


    private AuthorModel convertToAuthorModel(Author author) {
        return mapper.map(author, AuthorModel.class);
    }
}
