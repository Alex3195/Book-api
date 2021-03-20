package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author,Long> {
}
