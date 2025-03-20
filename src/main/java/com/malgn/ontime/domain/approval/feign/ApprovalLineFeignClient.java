package com.malgn.ontime.domain.approval.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-approval-lien")
public interface ApprovalLineFeignClient {

    @GetMapping(path = "api/v1/approval/lines")
    List<ApprovalLineResponse> getLines(@SpringQueryMap SearchApprovalLineRequest searchRequest);
}
