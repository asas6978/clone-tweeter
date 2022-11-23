package com.example.clone_tweeter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class CommentModel {
    private Long commentId;
    private String comment;
    private String hashtag;
    private Timestamp createAt;
    private Long userId;
    private Long tweetId;

    public CommentModel(String comment, String hashtag, Long userId, Long tweetId) {
        this.comment = comment;
        this.hashtag = hashtag;
        this.userId = userId;
        this.tweetId = tweetId;
    }

    public CommentModel(String comment, String hashtag, Timestamp createAt, Long userId, Long tweetId) {
        this.comment = comment;
        this.hashtag = hashtag;
        this.createAt = createAt;
        this.userId = userId;
        this.tweetId = tweetId;
    }

    public static boolean checkExistNull(CommentModel commentModel) {
        return commentModel.comment == null || commentModel.userId == null
                || commentModel.tweetId == null;
    }
}
