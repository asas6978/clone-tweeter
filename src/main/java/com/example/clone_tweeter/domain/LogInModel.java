package com.example.clone_tweeter.domain;

public class LogInModel {
    public String email;
    public String password;

    public LogInModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static boolean checkExistNull(LogInModel logInModel) {
        return logInModel.email == null || logInModel.password == null;
    }
}
