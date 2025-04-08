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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.user.feign.UserCompLeaveEntryFeignClient;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.model.SearchUserRequest;
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

    @GetMapping(path = "comp/leave/entries")
    public List<UserCompLeaveEntryResponse> getCompLeaveEntries(@AuthenticationPrincipal OidcUser oidcUser) {

        String uniqueId = AuthUtils.getUniqueId(oidcUser);

        return userCompLeaveEntryClient.getCompLeaveEntry(uniqueId);
    }
}
