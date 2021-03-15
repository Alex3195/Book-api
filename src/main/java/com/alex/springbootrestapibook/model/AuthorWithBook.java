package com.alex.springbootrestapibook.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWithBook extends AuthorModel {
    private List<BookModel> bookList;

    public AuthorWithBook(Long id, String name, List<BookModel> books) {
        super(id, name);
        this.bookList = books;
    }

}
