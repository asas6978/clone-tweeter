package com.example.clone_tweeter.Service;

import com.example.clone_tweeter.dao.CommentDao;
import com.example.clone_tweeter.domain.CommentModel;
import com.example.clone_tweeter.entity.Comments;
import com.example.clone_tweeter.repository.CommentRepositoryImpl;
import com.example.clone_tweeter.util.ExceptionMessage;
import com.example.clone_tweeter.util.Validation.CommentsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepositoryImpl commentRepository;
    private final CommentDao commentDao;

    public Long upload(CommentModel commentModel) throws IllegalArgumentException {
        Comments comment = commentRepository.save(convertToCommentsEntity(commentModel));
        return comment.getCommentId();
    }

    private Comments convertToCommentsEntity(CommentModel commentModel) {
        return Comments.put(commentModel);
    }

    public CommentModel lookUp(Long commentId) throws IllegalArgumentException {
        if (!commentDao.checkCommentExist(commentId)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMENT_ID);
        }
        return commentDao.lookUpByCommentId(commentId);
    }
}
