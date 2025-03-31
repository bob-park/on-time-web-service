package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;

@Builder
public record DocumentApprovalHistoryResponse(Long id,
                                              ApprovalLineResponse approvalLine,
                                              String status,
                                              String reason,
                                              LocalDateTime createdDate,
                                              String createdBy,
                                              LocalDateTime lastModifiedDate,
                                              String lastModifiedBy) {
}
