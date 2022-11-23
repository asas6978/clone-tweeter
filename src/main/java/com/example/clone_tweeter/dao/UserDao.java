package com.example.clone_tweeter.dao;

import com.example.clone_tweeter.domain.LogInModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public Long logIn(LogInModel logInModel) {
        String logInQuery = "select user_id from User where email = ? and password = ?";
        Object[] params = new Object[]{logInModel.email, logInModel.password};
        return this.jdbcTemplate.queryForObject(logInQuery, Long.class, params);
    }

    public boolean checkUserExist(LogInModel logInModel) {
        String checkUserQuery = "select exists(select user_id from User where email = ? and password = ?)";
        Object[] params = new Object[]{logInModel.email, logInModel.password};
        return Boolean.TRUE.equals(this.jdbcTemplate.
                queryForObject(checkUserQuery, boolean.class, params));
    }
}
