package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.domain.CommentModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class CommentControllerTest {
    @Autowired
    CommentController commentController;

    @DisplayName("댓글 업로드 성공 테스트")
    @Test
    void upload() {
        assertThat(commentController.upload(new CommentModel("testComment", null, 1L, 1L)))
                .isInstanceOf(Long.class);
    }

    @DisplayName("댓글 업로드 실패 테스트")
    @Test
    void uploadFail() {
        assertThatThrownBy(() -> commentController.upload(new CommentModel(null, null, 1L, 1L)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("댓글 조회 성공 테스트")
    @Test
    void lookUp() {
        assertThat(commentController.lookUp(1L))
                .isInstanceOf(CommentModel.class);
    }

    @DisplayName("댓글 조회 실패 테스트")
    @Test
    void lookUpFail() {
        assertThatThrownBy(() -> commentController.lookUp(1000L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
