package com.malgn.ontime.domain.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.malgn.ontime.domain.position.model.PositionResponse;

@FeignClient(name = "auth-user-api", contextId = "auth-user-api-position")
public interface UserPositionFeignClient {

    @GetMapping(path = "api/v1/positions")
    List<PositionResponse> getPositions();
}
