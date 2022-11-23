package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.domain.TweetModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class TweetControllerTest {
    private static final String SUCCEED = "성공적으로 처리하였습니다.";
    @Autowired TweetController tweetController;

    @DisplayName("트윗 업로드 성공 테스트")
    @Test
    void upload() {
        assertThat(tweetController.upload(new TweetModel(1L, "uploadTest")))
                .isInstanceOf(Long.class);
    }

    @DisplayName("트윗 업로드 실패 테스트")
    @Test
    void uploadFail() {
        assertThatThrownBy(() -> tweetController.upload(new TweetModel(1L, (String) null)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("리트윗 테스트")
    @Test
    void retweet() {
        assertThat(tweetController.retweet(new TweetModel(8L, 3L)))
                .isEqualTo(SUCCEED);
    }

    @DisplayName("트윗 조회 성공 테스트 (리트윗 X)")
    @Test
    void lookUp() {
        assertThat(tweetController.lookUp(1L))
                .isInstanceOf(TweetModel.class);
    }

    @DisplayName("트윗 조회 성공 테스트 (리트윗 O)")
    @Test
    void retweetLookUp() {
        assertThatThrownBy(() -> assertThat(tweetController.lookUp(1L).getRetweeterId()).isNull())
                .isInstanceOf(AssertionError.class);  // null이 아니므로 오류 발생
    }

    @DisplayName("트윗 조회 실패 테스트")
    @Test
    void lookUpFail() {
        assertThatThrownBy(() -> tweetController.lookUp(1000L))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("트윗 리스트 조회 테스트 (리트윗 포함)")
    @Test
    void getTweets() {
        List<TweetModel> tweets = tweetController.getTweets(1L);
        for (TweetModel tweet : tweets) {
            System.out.println(tweet.getTweetId() + " " +
                    tweet.getUserEmail() + " "  +
                    tweet.getContents() + " "  +
                    tweet.getCreateAt() + " "  +
                    tweet.getRetweeterEmail());
        }
    }
}
