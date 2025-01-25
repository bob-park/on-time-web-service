package com.malgn.ontime.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFeignClient userClient;

    public UserResponse me(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return userClient.getById(uniqueId);
    }

}
