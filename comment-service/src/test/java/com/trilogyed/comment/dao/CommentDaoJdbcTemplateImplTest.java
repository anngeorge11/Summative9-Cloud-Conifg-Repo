package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentDaoJdbcTemplateImplTest {

    @Autowired
    protected CommentDao dao;

//    @Autowired
//    protected PostDao postDao;

    @Before
    public void setUp() throws Exception {
        List<Comment> mList = dao.getAllComments();

        mList.stream()
                .forEach(comment -> dao.deleteComment(comment.getCommentId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteComment() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,06,13));
        comment.setCommenterName("Ann");
        comment.setComment("Hi, How are you");

        comment = dao.createComment(comment);

        Comment comment2 = dao.getComment(comment.getCommentId());

        assertEquals(comment.toString(), comment2.toString());
        dao.deleteComment(comment.getCommentId());
        comment2 = dao.getComment(comment.getCommentId());
        assertNull(comment2);


    }



    @Test
    public void getAllComments() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,06,13));
        comment.setCommenterName("Ann");
        comment.setComment("Hi, How are you");

        comment = dao.createComment(comment);

        Comment comment1 = new Comment();
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2019,06,14));
        comment1.setCommenterName("Neetha");
        comment1.setComment("Hi, Where is this place");

        comment1 = dao.createComment(comment1);

        List<Comment> commentList = dao.getAllComments();

        assertEquals(commentList.size(), 2);
    }

    @Test
    public void updateComment() {

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,06,13));
        comment.setCommenterName("Ann");
        comment.setComment("Hi, How are you");

        comment = dao.createComment(comment);

        comment.setCommenterName("Mary Ann");

        dao.updateComment(comment);

        Comment comment1 = dao.getComment(comment.getCommentId());


        assertEquals(comment1.toString(), comment.toString());
    }


}