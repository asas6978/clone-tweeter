package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.Service.UserService;
import com.example.clone_tweeter.domain.LogInModel;
import com.example.clone_tweeter.domain.UserModel;
import com.example.clone_tweeter.entity.User;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public User signUp(UserModel userModel) throws IllegalArgumentException {
        if (UserModel.checkExistNull(userModel)) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        return userService.save(userModel);
    }

    public Long logIn(LogInModel logInModel) throws IllegalArgumentException {
        if (LogInModel.checkExistNull(logInModel)) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        return userService.logIn(logInModel);
    }
}
