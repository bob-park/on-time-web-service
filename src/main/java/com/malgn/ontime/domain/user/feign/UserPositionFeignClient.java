package com.malgn.ontime.domain.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malgn.ontime.domain.position.model.PositionResponse;
import com.malgn.ontime.domain.user.model.UserPositionResponse;

@FeignClient(name = "on-time-api", contextId = "on-time-api-user-position")
public interface UserPositionFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}/position")
    UserPositionResponse getPosition(@PathVariable String uniqueId);

    @GetMapping(path = "api/v1/positions")
    List<PositionResponse> getPositions();
}
