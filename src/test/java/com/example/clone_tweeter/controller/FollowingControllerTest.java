package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.domain.FollowModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class FollowingControllerTest {
    private static final String SUCCEED = "성공적으로 처리하였습니다.";
    @Autowired FollowingController followingController;

    @DisplayName("자동 팔로잉/언팔로잉 테스트")
    @Test
    void autoFollow() {
        assertThat(followingController.follow(new FollowModel(3L, 4L)))
                .isEqualTo(SUCCEED);
    }

    @DisplayName("팔로잉 테스트")
    @Test
    void follow() {
        assertThat(followingController.following(new FollowModel(3L, 4L)))
                .isEqualTo(SUCCEED);
    }

    @DisplayName("팔로잉 실패 테스트")
    @Test
    void followFail() {
        assertThat(followingController.unFollowing(new FollowModel(3L, 4L)))
                .isEqualTo(SUCCEED);
    }

    @DisplayName("언팔로잉 테스트")
    @Test
    void unFollow() {
        assertThat(followingController.unFollowing(new FollowModel(3L, 4L)))
                .isEqualTo(SUCCEED);
    }

    @DisplayName("언팔로잉 실패 테스트")
    @Test
    void unFollowFail() {
        assertThatThrownBy(() -> followingController.unFollowing(new FollowModel(3L, 4L)))
                .isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName("팔로잉 목록 조회 테스트")
    @Test
    void getFollowing() {
        assertThat(followingController.getFollowing(6L))
                .isInstanceOf(List.class);
    }

    @DisplayName("팔로잉 목록 조회 실패 테스트")
    @Test
    void getFollowingFail() {
        assertThatThrownBy(() -> followingController.getFollowing(100L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("팔로워 목록 조회 테스트")
    @Test
    void getFollower() {
        assertThat(followingController.getFollower(1L))
                .isInstanceOf(List.class);
    }

    @DisplayName("팔로워 목록 조회 실패 테스트")
    @Test
    void getFollowerFail() {
        assertThatThrownBy(() -> followingController.getFollower(100L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
