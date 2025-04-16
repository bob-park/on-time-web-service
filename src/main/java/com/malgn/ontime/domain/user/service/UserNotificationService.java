package com.malgn.ontime.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.notification.model.SendNotificationMessageRequest;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserNotificationFeignClient;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserNotificationService {

    private final UserFeignClient userClient;
    private final UserNotificationFeignClient notificationClient;

    public UserResponse sendMessage(String uniqueId, SendNotificationMessageRequest request) {

        UserResponse user = userClient.getById(uniqueId);

        notificationClient.sendMessage(uniqueId, request);

        return user;
    }

}
