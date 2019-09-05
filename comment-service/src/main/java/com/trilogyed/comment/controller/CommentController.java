package com.trilogyed.comment.controller;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RefreshScope
public class CommentController {

    @Autowired
    CommentDao commentDao;



    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment) {
        commentDao.createComment(comment);
        return comment;
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Comment getComment(@PathVariable @Valid int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Comment id should be greater than 0");

        }
        return commentDao.getComment(id);
    }


    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@PathVariable int id, @RequestBody @Valid Comment comment) {
        List<Comment> commentList = commentDao.getAllComments();
        for (Comment c : commentList) {
            if (c.getCommentId() == id) {
                comment.setCommentId(id);

                commentDao.updateComment(comment);
                if (id != comment.getCommentId()) {
                    throw new IllegalArgumentException("Comment ID on path must match the ID in the Comment object");

                }
            }
        }
        return commentDao.getComment(id);

    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable @Valid int id) {

        if (id != commentDao.getComment(id).getCommentId()) {
            throw new IllegalArgumentException("Comment ID on path must match the ID in the Comment object.");
        }

        commentDao.deleteComment(id);
    }



}