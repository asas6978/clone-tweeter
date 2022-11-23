package com.example.clone_tweeter.controller;

import com.example.clone_tweeter.Service.FollowingService;
import com.example.clone_tweeter.domain.FollowModel;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FollowingController {
    private static final String SUCCEED = "성공적으로 처리하였습니다.";
    private final FollowingService followingService;

    public String follow(FollowModel followModel) throws IllegalArgumentException {
        if (FollowModel.checkExistNull(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        followingService.follow(followModel);
        return SUCCEED;
    }

    public String following(FollowModel followModel) throws IllegalArgumentException, DuplicateKeyException {
        if (FollowModel.checkExistNull(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        followingService.following(followModel);
        return SUCCEED;
    }

    public String unFollowing(FollowModel followModel) throws IllegalArgumentException {
        if (FollowModel.checkExistNull(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        followingService.unFollowing(followModel);
        return SUCCEED;
    }

    public List<FollowModel.FollowingModel> getFollowing(Long userId) throws IllegalArgumentException {
        if (userId == null) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        return followingService.getFollowing(userId);
    }

    public List<FollowModel.FollowerModel> getFollower(Long userId) throws IllegalArgumentException {
        if (userId == null) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_INFORMATION);
        }
        return followingService.getFollower(userId);
    }
}
