package com.alex.springbootrestapibook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    private Long id;
    private String name;
    private Double price;
    private Double rate;
    private Long categoryId;
    private String filePath;
    private String ISBN_10;
    private String description;
    private Long languageId;
}
