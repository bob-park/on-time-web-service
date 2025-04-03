package com.malgn.ontime.domain.user.service;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserLeaveEntryFeignClient;
import com.malgn.ontime.domain.user.feign.UserPositionFeignClient;
import com.malgn.ontime.domain.user.feign.UserTeamFeignClient;
import com.malgn.ontime.domain.user.model.UserLeaveEntryResponse;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFeignClient userClient;
    private final UserPositionFeignClient userPositionClient;
    private final UserTeamFeignClient userTeamClient;
    private final UserLeaveEntryFeignClient userLeaveEntryClient;

    public UserResponse me(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        UserResponse result = userClient.getById(uniqueId);
        UserLeaveEntryResponse leaveEntry = userLeaveEntryClient.getLeaveEntry(uniqueId, LocalDate.now().getYear());

        return result.toBuilder()
            .leaveEntry(leaveEntry)
            .build();
    }

    public Page<UserResponse> getUsers() {
        SimplePageImpl<UserResponse> users = userClient.getUsers();

        Page<UserResponse> page = users.toPage();

        return page.map(item -> userClient.getById(item.uniqueId()));
    }

    public ResponseEntity<Resource> getUserAvatar(String uniqueId){
        return userClient.getUserAvatar(uniqueId);
    }

}
