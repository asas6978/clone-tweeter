package com.example.clone_tweeter.repository;

import com.example.clone_tweeter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepositoryImpl extends JpaRepository<Tweet, Long> {
}
