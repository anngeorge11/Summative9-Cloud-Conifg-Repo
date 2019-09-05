package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentDaoJdbcTemplateImpl implements CommentDao {

    private static final String INSERT_COMMENT_SQL =
            "insert into comment (post_id, create_date, commenter_name, comment) values (?, ?, ?, ?)";
    private static final String SELECT_COMMENT_BY_ID_SQL =
            "select * from comment where comment_id = ?";
    private static final String SELECT_ALL_COMMENTS_SQL =
            "select * from comment";

    private static final String UPDATE_COMMENT_SQL =
            "update comment set post_id = ?, create_date = ?, commenter_name = ?, comment = ? where comment_id = ?";
    private static final String DELETE_COMMENT_SQL =
            "delete from comment where comment_id = ?";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CommentDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment createComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment());


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        comment.setCommentId(id);
        return comment;

    }

    @Override
    public Comment getComment(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_COMMENT_BY_ID_SQL, this::mapRowToComment, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SELECT_ALL_COMMENTS_SQL, this :: mapRowToComment);
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment(),
                comment.getCommentId());

    }

    @Override
    public void deleteComment(int id) {
        jdbcTemplate.update(DELETE_COMMENT_SQL, id);

    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment m1 = new Comment();
        m1.setCommentId(rs.getInt("comment_id"));
        m1.setPostId(rs.getInt("post_id"));
        m1.setCreateDate(rs.getDate("create_date").toLocalDate());
        m1.setCommenterName(rs.getString("commenter_name"));
        m1.setComment(rs.getString("comment"));
        return m1;
    }
}
