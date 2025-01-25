package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malgn.ontime.domain.team.model.TeamResponse;

@FeignClient(name = "on-time-api", contextId = "on-time-api-user-team")
public interface UserTeamFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}/team")
    TeamResponse getTeam(@PathVariable("uniqueId") String uniqueId);
}
