package com.example.clone_tweeter.entity;

import com.example.clone_tweeter.domain.UserModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private Long age;
    @Column
    private String birthday;
    @Column
    private String gender;

    @Transient
    public String year;
    @Transient
    public String month;
    @Transient
    public String day;

    public User(Long userId, String email, String name, Long age, String birthday, String gender) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
    }

    public static User put(UserModel userModel) {
        User user = new User();
        user.password = userModel.password;
        user.email = userModel.email;
        user.name = userModel.name;
        user.age = userModel.age;
        user.birthday = convertToBirthday(userModel);
        user.gender = userModel.gender;
        return user;
    }

    private static String convertToBirthday(UserModel userModel) {
        return userModel.year + userModel.month + userModel.day;
    }
}
