package com.shvarts.smartpresents.controllers;

import com.shvarts.smartpresents.dtos.UserStatus;
import com.shvarts.smartpresents.services.UserService;
import com.shvarts.smartpresents.services.UserStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/status/")
public class UserStatusController {

    private final UserStatusService userStatusService;
    private final UserService userService;

    public UserStatusController(UserStatusService userStatusService, UserService userService) {
        this.userStatusService = userStatusService;
        this.userService = userService;
    }

    @PostMapping(value = "updateStatus")
    public ResponseEntity<String> updateUserStatus(@RequestBody UserStatus userStatus) {
   // public ResponseEntity<String> updateUserStatus(@RequestBody Boolean isAtHome) {
       // String authenticatedUserId = SecurityContextHolder.getContext().getAuthentication().getName();
       // String name = userService.getUserName(authenticatedUserId);
       // UserStatus userStatus = new UserStatus(name, isAtHome);
        userStatusService.updateUserStatus(userStatus);
        return ResponseEntity.ok("Status updated successfully.");
    }
    @GetMapping("getStatuses")
    public ResponseEntity<List<UserStatus>> getStatuses(){
        List<String> userIds = userService.getAllUserIds();
        List<UserStatus> userStatusList = new ArrayList<>();
        for (String id : userIds) {
            UserStatus userStatus = userStatusService.getUserStatus(id);
            userStatusList.add(userStatus);
        }
        return ResponseEntity.ok(userStatusList);
    }
}