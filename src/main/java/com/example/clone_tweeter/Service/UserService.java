package com.example.clone_tweeter.Service;

import com.example.clone_tweeter.dao.UserDao;
import com.example.clone_tweeter.domain.LogInModel;
import com.example.clone_tweeter.domain.UserModel;
import com.example.clone_tweeter.entity.User;
import com.example.clone_tweeter.repository.UserRepositoryImpl;
import com.example.clone_tweeter.util.ExceptionMessage;
import com.example.clone_tweeter.util.Validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositoryImpl userRepository;
    private final UserDao userDao;

    public User save(UserModel userModel) throws IllegalArgumentException {
        checkInput(userModel);
        User user = convertToUserEntity(userModel);
        return userRepository.save(user);
    }

    private void checkInput(UserModel userModel) throws IllegalArgumentException {
        UserValidation.checkPassword(userModel.password);
        UserValidation.checkEmail(userModel.email);
        UserValidation.checkAge(userModel.age);
        UserValidation.checkBirthday(userModel.year, userModel.month, userModel.day);
    }

    private User convertToUserEntity(UserModel userModel) throws IllegalArgumentException {
        return User.put(userModel);
    }

    public Long logIn(LogInModel logInModel) throws IllegalArgumentException {
        if (!userDao.checkUserExist(logInModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        return userDao.logIn(logInModel);
    }
}
