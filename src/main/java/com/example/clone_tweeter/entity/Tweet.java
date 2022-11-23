package com.example.clone_tweeter.entity;

import com.example.clone_tweeter.domain.TweetModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private Long tweetId;
    @Column
    private String contents;
    @Column(name = "is_retweet")
    private Long isRetweet;
    @Column(name = "user_id")
    private Long userId;

    public static Tweet put(TweetModel tweetModel) {
        Tweet tweet = new Tweet();
        tweet.contents = tweetModel.getContents();
        tweet.isRetweet = 0L;
        tweet.userId = tweetModel.getUserId();
        return tweet;
    }
}
