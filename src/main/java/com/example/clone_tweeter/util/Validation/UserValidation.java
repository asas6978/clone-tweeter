package com.example.clone_tweeter.util.Validation;

import com.example.clone_tweeter.util.ExceptionMessage;

import java.util.regex.Pattern;

public class UserValidation {
    public static boolean checkPassword(String password) throws IllegalArgumentException {
        String passwordRegex = "^[A-Za-z[0-9]$@$!%*#?&]{8,10}$";
        if (!Pattern.matches(passwordRegex, password)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PASSWORD);
        }
        return true;
    }

    public static boolean checkEmail(String email) throws IllegalArgumentException {
        String emailRegex = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_EMAIL);
        }
        return true;
    }

    public static boolean checkAge(Long age) throws IllegalArgumentException {
        if (!(age > 0 && age < 150)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AGE);
        }
        return true;
    }

    //월별에 따른 일수, 윤년 고려 X
    public static boolean checkBirthday(String year, String month, String day) throws IllegalArgumentException {
        if (Integer.parseInt(year) < 1800 || Integer.parseInt(year) > 2022) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_YEAR);
        }
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONTH);
        }
        if (!(Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DAY);
        }
        return true;
    }
}
