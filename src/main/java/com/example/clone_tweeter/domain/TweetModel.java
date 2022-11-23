package com.example.clone_tweeter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TweetModel {
    private Long tweetId;
    private Long userId;
    private String userEmail;
    private String contents;
    private Timestamp createAt;
    private Long retweeterId;
    private String retweeterEmail;

    public TweetModel(Long userId, String contents) {  // 트윗 업로드 시 이용
        this.userId = userId;
        this.contents = contents;
        this.userEmail = null;
        this.retweeterId = null;
    }

    public TweetModel(Long tweetId, Long retweeterId) {
        this.tweetId = tweetId;
        this.retweeterId = retweeterId;
    }

    public TweetModel(Long tweetId, Long userId, String userEmail, String contents, Timestamp createAt) {  // 리트윗 안한 트윗 조회 시 이용
        this.tweetId = tweetId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.contents = contents;
        this.createAt = createAt;
        this.retweeterId = null;
    }

    public static boolean checkExistNull(TweetModel tweetModel) {
        return tweetModel.contents == null;
    }
}
