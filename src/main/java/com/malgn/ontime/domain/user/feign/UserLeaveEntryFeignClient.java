package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.malgn.ontime.domain.user.model.UserLeaveEntryResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-user-leave-entry")
public interface UserLeaveEntryFeignClient {

    @GetMapping(path = "api/v1/users/{userUniqueId}/leave/entries")
    UserLeaveEntryResponse getLeaveEntry(@PathVariable String userUniqueId, @RequestParam("year") int year);
}
