package com.alex.springbootrestapibook.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private Long id;
    private String content;
    private Timestamp createdAt;
}
