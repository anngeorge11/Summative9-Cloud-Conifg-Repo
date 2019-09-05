package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.model.Post;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-service")
public interface PostClient {

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable @Valid int id);

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post createPost(@RequestBody @Valid Post post);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public Post updatePost(@PathVariable int id, @RequestBody @Valid Post post);

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    public List<Post> getPostsByPosterName(@PathVariable @Valid String posterName);






}