package com.malgn.ontime.domain.user.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.user.feign.UserCompLeaveEntryFeignClient;
import com.malgn.ontime.domain.user.model.SearchUserRequest;
import com.malgn.ontime.domain.user.model.UpdateUserPasswordRequest;
import com.malgn.ontime.domain.user.model.UserCompLeaveEntryResponse;
import com.malgn.ontime.domain.user.model.UserResponse;
import com.malgn.ontime.domain.user.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserCompLeaveEntryFeignClient userCompLeaveEntryClient;

    private final UserService userService;

    @GetMapping(path = "")
    public Page<UserResponse> getUsers(SearchUserRequest searchRequest, Pageable pageable) {
        return userService.getUsers(searchRequest, pageable);
    }

    @GetMapping(path = "me")
    public UserResponse me(@AuthenticationPrincipal OidcUser oidcUser) {
        return userService.me(oidcUser);
    }

    @GetMapping(path = "{uniqueId}/avatar")
    public ResponseEntity<Resource> getUserAvatar(@PathVariable String uniqueId) {
        return userService.getUserAvatar(uniqueId);
    }

    @PostMapping(path = "avatar")
    public UserResponse updateUserAvatar(@AuthenticationPrincipal OidcUser user,
        @RequestPart("avatar") MultipartFile avatar) {
        return userService.updateUserAvatar(user, avatar);
    }

    @PostMapping(path = "avatar/reset")
    public UserResponse resetUserAvatar(@AuthenticationPrincipal OidcUser user) {
        return userService.resetUserAvatar(user);
    }

    @GetMapping(path = "comp/leave/entries")
    public List<UserCompLeaveEntryResponse> getCompLeaveEntries(@AuthenticationPrincipal OidcUser oidcUser) {

        String uniqueId = AuthUtils.getUniqueId(oidcUser);

        return userCompLeaveEntryClient.getCompLeaveEntry(uniqueId);
    }

    @PostMapping(path = "password")
    public UserResponse updatePassword(@AuthenticationPrincipal OidcUser user,
        @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        return userService.updatePassword(user, updateUserPasswordRequest);
    }
}
