package com.alex.springbootrestapibook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksWithCategoryIdAndRateModel extends BookModel {
    private Long categoryId;
    private Double rate;
}
