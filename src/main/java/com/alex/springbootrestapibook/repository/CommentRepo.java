package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(Long bookId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByTime(Timestamp timestamp);
}
