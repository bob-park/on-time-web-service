package com.malgn.ontime.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.user.model.UserResponse;
import com.malgn.ontime.domain.user.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "")
    public SimplePageImpl<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "me")
    public UserResponse me(@AuthenticationPrincipal OidcUser oidcUser) {
        return userService.me(oidcUser);
    }

}
