package com.malgn.ontime.domain.approval.feign;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SearchApprovalLineRequest {

    private Long teamId;
    private String documentType;

}
