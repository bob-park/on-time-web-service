package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.user.model.SearchUserRequest;
import com.malgn.ontime.domain.user.model.UpdateUserPasswordRequest;
import com.malgn.ontime.domain.user.model.UserResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api")
public interface UserFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}")
    UserResponse getById(@PathVariable String uniqueId);

    @GetMapping(path = "api/v1/users")
    SimplePageImpl<UserResponse> getUsers(@SpringQueryMap SearchUserRequest searchRequest, Pageable pageable);

    @GetMapping(path = "api/v1/users/{uniqueId}/avatar")
    ResponseEntity<Resource> getUserAvatar(@PathVariable String uniqueId);

    @PostMapping(path = "api/v1/users/{uniqueId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UserResponse updateUserAvatar(@PathVariable String uniqueId, @RequestPart("avatar") MultipartFile avatar);

    @PostMapping(path = "api/v1/users/{uniqueId}/avatar/reset")
    UserResponse resetUserAvatar(@PathVariable String uniqueId);

    @PostMapping(path = "api/v1/users/{uniqueId}/password")
    UserResponse updatePassword(@PathVariable String uniqueId, @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest);
}
