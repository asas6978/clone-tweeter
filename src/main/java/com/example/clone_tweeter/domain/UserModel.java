package com.example.clone_tweeter.domain;

public class UserModel {
    public String password;
    public String email;
    public String name;
    public Long age;
    public String gender;

    public String year;
    public String month;
    public String day;

    public UserModel(String password, String email, String name,
                     int age, String gender, String year, String month, String day) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = (long) age;
        this.gender = gender;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static boolean checkExistNull(UserModel userModel) {
        return userModel.password == null || userModel.email == null
                || userModel.name == null || userModel.age == null || userModel.gender == null
                || userModel.year == null || userModel.month == null || userModel.day == null;
    }
}
