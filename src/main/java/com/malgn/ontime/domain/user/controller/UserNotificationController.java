package com.malgn.ontime.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.notification.model.SendNotificationMessageRequest;
import com.malgn.ontime.domain.user.model.UserResponse;
import com.malgn.ontime.domain.user.service.UserNotificationService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "users/{uniqueId}/notification")
public class UserNotificationController {

    private final UserNotificationService userNotificationService;

    @PostMapping(path = "send")
    public UserResponse sendMessage(@PathVariable String uniqueId,
        @RequestBody SendNotificationMessageRequest request) {
        return userNotificationService.sendMessage(uniqueId, request);
    }

}
