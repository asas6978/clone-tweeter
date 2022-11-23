package com.example.clone_tweeter.dao;

import com.example.clone_tweeter.domain.TweetModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TweetDao {
    private final JdbcTemplate jdbcTemplate;

    public void retweet(TweetModel tweetModel) {
        String updateQuery = "update Tweet set is_retweet = 1 where tweet_id = ?";
        String insertQuery = "insert Retweet(tweet_id, retweeter_id) values (?, ?)";
        Object[] params = new Object[]{tweetModel.getTweetId(), tweetModel.getRetweeterId()};
        this.jdbcTemplate.update(updateQuery, tweetModel.getTweetId());
        this.jdbcTemplate.update(insertQuery, params);
    }

    public TweetModel lookUpByTweetId(Long tweetId) {
        String retweetQuery = "select u1.user_id, u1.email, contents, retweeter_id, u2.email, r.create_at from Tweet as t\n" +
                "    join User as u1 using(user_id)\n" +
                "    join Retweet as r on t.tweet_id = r.tweet_id and t.tweet_id = ?\n" +
                "    join User as u2 on u2.user_id = retweeter_id";
        String tweetQuery = "select user_id, email, contents, create_at from Tweet\n" +
                "    join User using (user_id) where tweet_id = ?";

        if (isRetweeted(tweetId)) {
            return this.jdbcTemplate.queryForObject(retweetQuery,
                    (rs, rowNum) -> new TweetModel(
                            tweetId,
                            rs.getLong("u1.user_id"),
                            rs.getString("u1.email"),
                            rs.getString("contents"),
                            rs.getTimestamp("create_at"),
                            rs.getLong("retweeter_id"),
                            rs.getString("u2.email")
                    ), tweetId);
        }
        return this.jdbcTemplate.queryForObject(tweetQuery,
                (rs, rowNum) -> new TweetModel(
                    tweetId,
                    rs.getLong("user_id"),
                    rs.getString("email"),
                    rs.getString("contents"),
                    rs.getTimestamp("create_at")
            ), tweetId);
    }

    public boolean checkTweetExist(Long tweetId) {
        String checkTweetQuery = "select exists(select contents from Tweet where tweet_id = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.
                queryForObject(checkTweetQuery, boolean.class, tweetId));
    }

    private boolean isRetweeted(Long tweetId) {
        String checkRetweetQuery = "select exists(select contents from Tweet where tweet_id = ? and is_retweet = 1)";
        return Boolean.TRUE.equals(this.jdbcTemplate.
                queryForObject(checkRetweetQuery, boolean.class, tweetId));
    }

    public List<TweetModel> getTweets(Long userId) {
        List<TweetModel> tweets;
        tweets = getOriginalTweets(userId);
        tweets.addAll(getRetweets(userId));
        return tweets;
    }

    public List<TweetModel> getOriginalTweets(Long userId) {
        String tweetQuery = "select t.tweet_id, u.email, contents, create_at from Tweet as t\n" +
                " join User as u on u.user_id = t.user_id where t.user_id = ?";

        return this.jdbcTemplate.query(tweetQuery,
                (rs, rowNum) -> new TweetModel(
                        rs.getLong("t.tweet_id"),
                        userId,
                        rs.getString("u.email"),
                        rs.getString("contents"),
                        rs.getTimestamp("create_at")
                ), userId);
    }

    public List<TweetModel> getRetweets(Long userId) {
        String retweetQuery = "select t.tweet_id, u1.email, contents, retweeter_id, u2.email, r.create_at from Tweet as t\n" +
                "    join User as u1 using(user_id)\n" +
                "    join Retweet as r on t.tweet_id = r.tweet_id and r.retweeter_id = ?\n" +
                "    join User as u2 on u2.user_id = retweeter_id";

        return this.jdbcTemplate.query(retweetQuery,
                (rs, rowNum) -> new TweetModel(
                        rs.getLong("t.tweet_id"),
                        userId,
                        rs.getString("u1.email"),
                        rs.getString("contents"),
                        rs.getTimestamp("create_at"),
                        rs.getLong("retweeter_id"),
                        rs.getString("u2.email")
                ), userId);
    }

    public boolean checkUserExist(Long userId) {
        String checkUserQuery = "select exists(select email from User where user_id = ?)";
        return Boolean.TRUE.equals(this.jdbcTemplate
                .queryForObject(checkUserQuery, boolean.class, userId));
    }
}
