package com.example.clone_tweeter.dao;

import com.example.clone_tweeter.domain.FollowModel;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowingDao {
    private final JdbcTemplate jdbcTemplate;

    public void follow(FollowModel followModel) throws DuplicateKeyException {
        try {
            String followQuery = "insert into Following(following, follower) values (?, ?)";
            Object[] params = new Object[]{followModel.getFollowingId(), followModel.getFollowerId()};
            this.jdbcTemplate.update(followQuery, params);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(ExceptionMessage.ALREADY_FOLLOWING);
        }
    }

    public void unFollow(FollowModel followModel) {
        String unFollowQuery = "delete from Following where following = ? and follower = ?";
        Object[] params = new Object[]{followModel.getFollowingId(), followModel.getFollowerId()};
        this.jdbcTemplate.update(unFollowQuery, params);
    }

    // 특정 유저가 팔로잉하는 유저 조회
    public List<FollowModel.FollowingModel> lookUpByFollowerId(Long userId) {
        String lookUpQuery = "select following from Following where follower = ?";
        return this.jdbcTemplate.query(lookUpQuery,
                (rs, rowNum) -> new FollowModel.FollowingModel(rs.getLong("following")),
                userId);
    }

    // 특정 유저를 팔로우하는 유저 조회
    public List<FollowModel.FollowerModel> lookUpByFollowingId(Long userId) {
        String lookUpQuery = "select follower from Following where following = ?";
        return this.jdbcTemplate.query(lookUpQuery,
                (rs, rowNum) -> new FollowModel.FollowerModel(rs.getLong("follower")),
                userId);
    }

    public boolean checkUsersExist(FollowModel followModel) {
        String checkFollowingIdQuery = "select exists(select email from User where user_id = ?)";
        String checkFollowerIdQuery = "select exists(select email from User where user_id = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate
                .queryForObject(checkFollowingIdQuery, boolean.class, followModel.getFollowingId())) &&
                Boolean.TRUE.equals(this.jdbcTemplate
                        .queryForObject(checkFollowerIdQuery, boolean.class, followModel.getFollowerId()));
    }

    public boolean checkUserExist(Long userId) {
        String checkUserQuery = "select exists(select email from User where user_id = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate
                .queryForObject(checkUserQuery, boolean.class, userId));
    }

    public boolean checkFollowing(FollowModel followModel) {
        String checkFollowingQuery = "select exists(select following from Following where follower = ?)";
        String checkFollowerQuery = "select exists(select follower from Following where following = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate
                .queryForObject(checkFollowingQuery, boolean.class, followModel.getFollowerId())) &&
                Boolean.TRUE.equals(this.jdbcTemplate
                        .queryForObject(checkFollowerQuery, boolean.class, followModel.getFollowingId()));
    }
}
