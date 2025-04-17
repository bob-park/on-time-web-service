package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.user.model.SearchUserEmploymentRequest;
import com.malgn.ontime.domain.user.model.UserEmploymentResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-user-employment")
public interface UserEmploymentFeignClient {

    @GetMapping(path = "api/v1/users/employments")
    SimplePageImpl<UserEmploymentResponse> search(@SpringQueryMap SearchUserEmploymentRequest searchRequest, Pageable pageable);
}
