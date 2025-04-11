package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;

@Builder(toBuilder = true)
public record DocumentApprovalHistoryResponse(Long id,
                                              DocumentResponse document,
                                              ApprovalLineResponse approvalLine,
                                              String status,
                                              String reason,
                                              LocalDateTime createdDate,
                                              String createdBy,
                                              LocalDateTime lastModifiedDate,
                                              String lastModifiedBy) {
}
