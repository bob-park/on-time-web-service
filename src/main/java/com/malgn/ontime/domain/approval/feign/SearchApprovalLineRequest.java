package com.malgn.ontime.domain.approval.feign;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class SearchApprovalLineRequest {

    private Long teamId;
    private String documentType;

    @Builder
    private SearchApprovalLineRequest(Long teamId, String documentType) {
        this.teamId = teamId;
        this.documentType = documentType;
    }
}
