package com.alex.springbootrestapibook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "authorId")
    private Long authorId;

    @Column(name="price")
    private Double price;

    @Column(name="categoryId")
    private Long categoryId;

    @Column(name="filePath")
    private String filePath;
    
    @Column(name = "ISBN_10")
    private String ISBN_10;

    @Column(name = "description")
    private String description;

    @Column(name = "languageId")
    private Long languageId;
}
