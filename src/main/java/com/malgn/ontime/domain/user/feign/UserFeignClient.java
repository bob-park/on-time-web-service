package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.user.model.UserResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api")
public interface UserFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}")
    UserResponse getById(@PathVariable String uniqueId);

    @GetMapping(path = "api/v1/users")
    SimplePageImpl<UserResponse> getUsers();
}
