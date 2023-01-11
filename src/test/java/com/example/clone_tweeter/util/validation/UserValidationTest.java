package com.example.clone_tweeter.util.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserValidationTest {

    @Test
    void checkPassword() {
        String password = "abcd1234";
        assertThat(UserValidation.checkPassword(password)).isEqualTo(true);
    }

    @Test
    void checkInvalidPassword() {
        String password = "abcd";
        assertThatThrownBy(() -> UserValidation.checkPassword(password))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEmail() {
        String email = "abcd@gmail.com";
        assertThat(UserValidation.checkEmail(email)).isEqualTo(true);
    }

    @Test
    void checkInvalidEmail() {
        String email = "abcd@gmailcom";
        assertThatThrownBy(() -> UserValidation.checkEmail(email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkAge() {
        Long age = 23L;
        assertThat(UserValidation.checkAge(age)).isEqualTo(true);
    }

    @Test
    void checkInvalidAge() {
        Long age = 0L;
        assertThatThrownBy(() -> UserValidation.checkAge(age))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkBirthday() {
        String year = "2001";
        String month = "02";
        String day = "21";
        assertThat(UserValidation.checkBirthday(year, month, day)).isEqualTo(true);
    }

    @Test
    void checkInvalidBirthday() {
        String year = "-1";
        String month = "02";
        String day = "21";
        assertThatThrownBy(() -> UserValidation.checkBirthday(year, month, day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}