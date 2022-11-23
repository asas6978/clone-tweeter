package com.example.clone_tweeter.entity;

import com.example.clone_tweeter.domain.CommentModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column
    private String comment;
    @Column
    private String hashtag;
    @Column
    private String createAt;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "tweet_id")
    private Long tweetId;

    public static Comments put(CommentModel commentModel) {
        Comments comments = new Comments();
        comments.comment = commentModel.getComment();
        comments.userId = comments.getUserId();
        comments.tweetId = comments.getTweetId();
        if (commentModel.getHashtag() != null) {
            comments.hashtag = commentModel.getHashtag();
        }
        return comments;
    }
}
