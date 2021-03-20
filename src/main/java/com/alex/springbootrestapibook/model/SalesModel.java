package com.alex.springbootrestapibook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesModel {
    private Long id;
    private Long bookId;
    private Double amount;
    private Long count;
    private Long userId;
    private Timestamp timestamp;
}
