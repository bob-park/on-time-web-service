package com.malgn.ontime.domain.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malgn.ontime.domain.user.model.UserCompLeaveEntryResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-user-comp-leave-entry")
public interface UserCompLeaveEntryFeignClient {

    @GetMapping(path = "api/v1/users/{userUniqueId}/comp/leave/entries")
    List<UserCompLeaveEntryResponse> getCompLeaveEntry(@PathVariable String userUniqueId);

}
