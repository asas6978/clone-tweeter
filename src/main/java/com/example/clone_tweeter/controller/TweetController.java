package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.Service.TweetService;
import com.example.clone_tweeter.domain.TweetModel;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TweetController {
    private static final String SUCCEED = "성공적으로 처리하였습니다.";
    private final TweetService tweetService;

    public Long upload(TweetModel tweetModel) throws IllegalArgumentException {
        if (TweetModel.checkExistNull(tweetModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TWEET_CONTENT);
        }
        return tweetService.upload(tweetModel);
    }

    public String retweet(TweetModel tweetModel) throws IllegalArgumentException {
        if (tweetModel.getTweetId() == null || tweetModel.getRetweeterId() == null) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        tweetService.retweet(tweetModel);
        return SUCCEED;
    }

    public TweetModel lookUp(Long userId) throws IllegalArgumentException {
        return tweetService.lookUp(userId);
    }

    public List<TweetModel> getTweets(Long userId) throws IllegalArgumentException {
        return tweetService.getTweets(userId);
    }
}
