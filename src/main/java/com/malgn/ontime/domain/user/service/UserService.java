package com.malgn.ontime.domain.user.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.position.model.PositionResponse;
import com.malgn.ontime.domain.team.model.TeamResponse;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserPositionFeignClient;
import com.malgn.ontime.domain.user.feign.UserTeamFeignClient;
import com.malgn.ontime.domain.user.model.UserPositionResponse;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFeignClient userClient;
    private final UserPositionFeignClient userPositionClient;
    private final UserTeamFeignClient userTeamClient;

    public UserResponse me(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        UserResponse result = userClient.getById(uniqueId);
        UserPositionResponse position = userPositionClient.getPosition(uniqueId);
        TeamResponse team = userTeamClient.getTeam(uniqueId);

        return result.toBuilder()
            .position(position.position())
            .team(team)
            .build();
    }

    public Page<UserResponse> getUsers() {
        SimplePageImpl<UserResponse> users = userClient.getUsers();

        Page<UserResponse> page = users.toPage();

        return page.map(item -> {

            UserPositionResponse position = null;
            TeamResponse team = null;

            try {
                position = userPositionClient.getPosition(item.uniqueId());
                team = userTeamClient.getTeam(item.uniqueId());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return item.toBuilder()
                .position(position != null ? position.position() : null)
                .team(team)
                .build();
        });
    }

    private PositionResponse findPosition(List<PositionResponse> positions, Long positionId) {
        return positions.stream().filter(item -> item.id().equals(positionId))
            .findAny()
            .orElse(null);
    }

    private TeamResponse findTeam(List<TeamResponse> teams, Long teamId) {
        return teams.stream().filter(item -> item.id().equals(teamId))
            .findAny()
            .orElse(null);
    }

}
