package com.alex.springbootrestapibook.repository;

import java.util.List;

import com.alex.springbootrestapibook.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(Long bookId);
}
