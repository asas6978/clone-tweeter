package com.example.clone_tweeter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Retweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tweet_id")
    private Long tweetId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "retweeter_id")
    private Long retweeterId;
    @Column
    private String createAt;
}
