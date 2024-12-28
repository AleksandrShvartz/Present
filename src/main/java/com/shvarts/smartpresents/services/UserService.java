package com.shvarts.smartpresents.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<String, String> userMap = new HashMap<>();

    public UserService() {
        userMap.put("1", "Саша");
        userMap.put("2", "Тоша");
        userMap.put("3", "Мама");
        userMap.put("4", "Папа");
    }

    public String getUserName(String id) {
        return userMap.get(id);
    }

    public List<String> getAllUserIds() {
        return new ArrayList<>(userMap.keySet());
    }
}