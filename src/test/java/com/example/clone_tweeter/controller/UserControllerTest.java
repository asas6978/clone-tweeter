package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.domain.LogInModel;
import com.example.clone_tweeter.domain.UserModel;
import com.example.clone_tweeter.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @DisplayName("회원가입 성공 테스트")
    @Test
    void signUp() {
       UserModel userModel = new UserModel("abcd1234", "abcdd@gmail.com", "test1",
                11, "f", "2013", "11", "20");

        assertThat(userController.signUp(userModel)).isInstanceOf(User.class);
    }

    @DisplayName("정보 미입력 시 회원가입 실패 테스트")
    @Test
    void signUpFail() {
        UserModel userModel = new UserModel(null, "abcd@gmail.com", "test1",
                11, "f", "2013", "11", "20");

        assertThatThrownBy(() -> userController.signUp(userModel))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로그인 성공 테스트")
    @Test
    void login() {
        assertThat(userController.logIn(new LogInModel("test@gmail.com", "abc1234")))
                .isEqualTo(1);
    }

    @DisplayName("로그인 실패 테스트")
    @Test
    void loginFail() {
        assertThatThrownBy(() -> userController.logIn(new LogInModel("test@gmail.com", "abc12345")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
