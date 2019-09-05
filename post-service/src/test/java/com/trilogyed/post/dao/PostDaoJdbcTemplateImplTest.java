package com.trilogyed.post.dao;


import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostDaoJdbcTemplateImplTest {

    @Autowired
    protected PostDao dao;

    @Before
    public void setUp() throws Exception {
        List<Post> mList = dao.getAllPosts();

        mList.stream()
                .forEach(post -> dao.deletePost(post.getPostID()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeletePost() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 07, 14));
        post.setPosterName("Annie");
        post.setPost("Hi, This is Annie");

        post = dao.createPost(post);

        Post post2 = dao.getPost(post.getPostID());
        assertEquals(post.toString(), post2.toString());
        dao.deletePost(post.getPostID());
        post2 = dao.getPost(post.getPostID());
        assertNull(post2);

    }

    @Test
    public void getAllPosts() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 07, 14));
        post.setPosterName("Annie");
        post.setPost("Hi, This is Annie");

        post = dao.createPost(post);

        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2019, 07, 15));
        post1.setPosterName("John");
        post1.setPost("Hi, This is John");

        post1 = dao.createPost(post1);

        List<Post> postList = dao.getAllPosts();

        assertEquals(postList.size(), 2);
    }

    @Test
    public void updatePost() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 07, 14));
        post.setPosterName("Annie");
        post.setPost("Hi, This is Annie");

        post = dao.createPost(post);

       post.setPost("How are you");

        dao.updatePost(post);

        Post post1 = dao.getPost(post.getPostID());

        assertEquals(post1.toString(), post.toString());
    }

    @Test
    public void getPostsByPosterName() {

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019, 07, 14));
        post.setPosterName("Annie");
        post.setPost("Hi, This is Annie");

        post = dao.createPost(post);

        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2019, 07, 15));
        post1.setPosterName("John");
        post1.setPost("Hi, This is John");

        post1 = dao.createPost(post1);

        List<Post> mList = dao.getPostsByPosterName("Annie");

        assertEquals(mList.size(), 1);


    }


}
