package com.shvarts.smartpresents.services;

import com.shvarts.smartpresents.dtos.UserStatus;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserStatusService {

    private final StringRedisTemplate redisTemplate;
    private final UserService userService;

    public UserStatusService(StringRedisTemplate redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }

    public void updateUserStatus(UserStatus userStatus) {
        redisTemplate.opsForValue().set(userStatus.name(), userStatus.isAtHome().toString());
    }

    public UserStatus getUserStatus(String id) {
        String isAtHomeStr = redisTemplate.opsForValue().get(id);
        Boolean isAtHome = Boolean.valueOf(isAtHomeStr);
        String name = userService.getUserName(id);
        return new UserStatus(name, isAtHome);
    }
}