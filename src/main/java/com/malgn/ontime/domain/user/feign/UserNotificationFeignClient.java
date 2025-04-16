package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malgn.notification.model.SendNotificationMessageRequest;

@FeignClient(name = "notification-api", contextId = "notification-api-user")
public interface UserNotificationFeignClient {

    @PostMapping(path = "api/v1/users/{uniqueId}/notification/send")
    void sendMessage(@PathVariable String uniqueId, @RequestBody SendNotificationMessageRequest request);

}
