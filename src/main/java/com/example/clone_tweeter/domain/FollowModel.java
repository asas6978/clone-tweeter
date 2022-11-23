package com.example.clone_tweeter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowModel {
    private Long followingId;
    private Long followerId;

    public static boolean checkExistNull(FollowModel followModel) {
        return followModel.followerId == null || followModel.followingId == null;
    }

    @AllArgsConstructor
    public static class FollowingModel {  // 팔로잉 조회 목적
        private Long followingId;
    }

    @AllArgsConstructor
    public static class FollowerModel {  // 팔로워 조회 목적
        private Long followerId;
    }
}




