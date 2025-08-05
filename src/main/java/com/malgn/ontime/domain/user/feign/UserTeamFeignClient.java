package com.malgn.ontime.domain.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.malgn.ontime.domain.team.model.TeamResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api-group")
public interface UserTeamFeignClient {

    @GetMapping(path = "api/v1/groups")
    List<TeamResponse> getTeams();

}
