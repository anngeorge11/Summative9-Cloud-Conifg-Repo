package com.trilogyed.post.controller;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@RestController
@CrossOrigin
@RefreshScope
public class PostController {

    @Autowired
    PostDao postDao;



    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid Post post) {
        postDao.createPost(post);
        return post;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Post getPost(@PathVariable @Valid int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Post id should be greater than 0");

        }
        return postDao.getPost(id);
    }

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getPostsByPosterName(@PathVariable @Valid String posterName) {
        if (postDao.getPostsByPosterName(posterName).isEmpty()){
            throw new NoSuchElementException("There is no post in the list that matches the poster name sent");
        }
        return postDao.getPostsByPosterName(posterName);
    }


    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody @Valid Post post) {
        List<Post> postList = postDao.getAllPosts();
        for (Post c : postList) {
            if (c.getPostID() == id) {
                post.setPostID(id);
                postDao.updatePost(post);
                if (id != post.getPostID()) {
                    throw new IllegalArgumentException("Post ID on path must match the ID in the Post object");

                }
            }
        }
        return postDao.getPost(id);

    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable @Valid int id) {

        if (id != postDao.getPost(id).getPostID()) {
            throw new IllegalArgumentException("Post ID on path must match the ID in the Post object.");
        }

        postDao.deletePost(id);
    }
}
