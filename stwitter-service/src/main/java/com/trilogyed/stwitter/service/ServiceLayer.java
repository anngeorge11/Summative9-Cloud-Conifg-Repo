package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.model.StwitterViewModel;
import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceLayer {

    @Autowired
    private final PostClient client;

    @Autowired
    private final CommentClient commentClient;

    public ServiceLayer(PostClient client, CommentClient commentClient){
        this.client = client;
        this.commentClient = commentClient;

    }

    @Transactional
    public StwitterViewModel createPost(int id) {
        StwitterViewModel svm = new StwitterViewModel();
       return svm;
    }


}
