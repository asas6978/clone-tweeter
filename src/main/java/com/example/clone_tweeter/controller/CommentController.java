package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.Service.CommentService;
import com.example.clone_tweeter.domain.CommentModel;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    public Long upload(CommentModel commentModel) throws IllegalArgumentException {
        if (CommentModel.checkExistNull(commentModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMENT_CONTENT);
        }
        return commentService.upload(commentModel);
    }

    public CommentModel lookUp(Long commentId) throws IllegalArgumentException {
        return commentService.lookUp(commentId);
    }
}
