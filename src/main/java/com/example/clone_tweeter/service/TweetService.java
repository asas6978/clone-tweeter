package com.example.clone_tweeter.service;

import com.example.clone_tweeter.dao.TweetDao;
import com.example.clone_tweeter.domain.TweetModel;
import com.example.clone_tweeter.entity.Tweet;
import com.example.clone_tweeter.repository.TweetRepositoryImpl;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepositoryImpl tweetRepository;
    private final TweetDao tweetDao;

    public Long upload(TweetModel tweetModel) {
        Tweet tweet = tweetRepository.save(convertToTweetEntity(tweetModel));
        return tweet.getTweetId();
    }

    private Tweet convertToTweetEntity(TweetModel tweetModel) {
        return Tweet.put(tweetModel);
    }

    public void retweet(TweetModel tweetModel) {
        if (!tweetDao.checkTweetExist(tweetModel.getTweetId())) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TWEET_ID);
        }
        if (!tweetDao.checkUserExist(tweetModel.getRetweeterId())) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        tweetDao.retweet(tweetModel);
    }

    public TweetModel lookUp(Long userId) throws IllegalArgumentException {
        if (!tweetDao.checkTweetExist(userId)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TWEET_ID);
        }
        return tweetDao.lookUpByTweetId(userId);
    }

    public List<TweetModel> getTweets(Long userId) {
        if (!tweetDao.checkTweetExist(userId)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TWEET_ID);
        }
        return tweetDao.getTweets(userId);
    }
}
