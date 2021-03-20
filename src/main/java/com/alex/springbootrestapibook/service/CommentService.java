package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Comment;
import com.alex.springbootrestapibook.model.CommentModel;
import com.alex.springbootrestapibook.repository.CommentRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    private ModelMapper mapper;
    private final String OLD_FORMAT = "yyyy-MM-dd";
    private final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public void save(CommentModel commentModel) {
        Comment comment = mapper.map(commentModel, Comment.class);
        commentRepo.save(comment);
    }

    public List<CommentModel> getCommentByBookId(Long bookId) {
        List<Comment> comments = commentRepo.findByBookId(bookId);
        List<CommentModel> result = comments.stream().map(this::convertToCommentModel).collect(Collectors.toList());
        return result;
    }

    public List<CommentModel> getCommentsByuserId(Long userId) {
        List<Comment> comments = commentRepo.findByUserId(userId);
        List<CommentModel> result = comments.stream().map(this::convertToCommentModel).collect(Collectors.toList());
        return result;
    }

    public List<CommentModel> getCommentsByTime(String time) {
        List<CommentModel> result = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(time);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            List<Comment> comments = commentRepo.findByTime(timestamp);
            result = comments.stream().map(this::convertToCommentModel).collect(Collectors.toList());
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return result;

    }

    private CommentModel convertToCommentModel(Comment comment) {
        return mapper.map(comment, CommentModel.class);
    }
}
