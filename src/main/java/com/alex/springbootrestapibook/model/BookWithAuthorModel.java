package com.alex.springbootrestapibook.model;

import com.alex.springbootrestapibook.entity.Author;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookWithAuthorModel extends BookModel {
    private AuthorModel author;

    public BookWithAuthorModel(Author authorEntity, Long id, String name, Double price, Double rate, Long categoryId,
                               String filePath, String ISBN_10, String description, Long languageId) {
        super(id, name, price, rate, categoryId, filePath, ISBN_10, description, languageId);
        this.author = new AuthorModel(authorEntity.getId(), authorEntity.getName());

    }
}
