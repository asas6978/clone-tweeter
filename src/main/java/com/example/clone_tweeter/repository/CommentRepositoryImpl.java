package com.example.clone_tweeter.repository;

import com.example.clone_tweeter.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoryImpl extends JpaRepository<Comments, Long> {
}
