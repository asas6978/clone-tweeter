package com.example.clone_tweeter.dao;

import com.example.clone_tweeter.domain.CommentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public CommentModel lookUpByCommentId(Long commentId) {
        String lookUpQuery = "select comment, hashtag, create_at, user_id, tweet_id from Comments where comment_id = ?";
        return this.jdbcTemplate.queryForObject(lookUpQuery,
                (rs, rowNum) -> new CommentModel(
                        rs.getString("comment"),
                        rs.getString("hashtag"),
                        rs.getTimestamp("create_at"),
                        rs.getLong("user_id"),
                        rs.getLong("tweet_id")
                ), commentId);
    }

    public boolean checkCommentExist(Long commentId) {
        String checkCommentQuery = "select exists(select comment from Comments where comment_id = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate
                .queryForObject(checkCommentQuery, boolean.class, commentId));
    }
}
