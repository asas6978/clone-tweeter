package com.example.clone_tweeter.service;

import com.example.clone_tweeter.dao.FollowingDao;
import com.example.clone_tweeter.domain.FollowModel;
import com.example.clone_tweeter.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowingService {
    private final FollowingDao followingDao;

    public void follow(FollowModel followModel) {
        if (!followingDao.checkUsersExist(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        if (followingDao.checkFollowing(followModel)) {  // 이미 팔로우 중이라면
            followingDao.unFollow(followModel);  // 언팔로우
        } else {
            followingDao.follow(followModel);
        }
    }

    public void following(FollowModel followModel) {
        if (!followingDao.checkUsersExist(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        followingDao.follow(followModel);
    }

    public void unFollowing(FollowModel followModel) {
        if (!followingDao.checkUsersExist(followModel)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        followingDao.unFollow(followModel);
    }

    public List<FollowModel.FollowingModel> getFollowing(Long userId) {
        if (!followingDao.checkUserExist(userId)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        return followingDao.lookUpByFollowerId(userId);
    }

    public List<FollowModel.FollowerModel> getFollower(Long userId) {
        if (!followingDao.checkUserExist(userId)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER);
        }
        return followingDao.lookUpByFollowingId(userId);
    }
}
