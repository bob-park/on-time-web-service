package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;

import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;

public record DocumentApprovalHistoryResponse(Long id,
                                              ApprovalLineResponse line,
                                              String status,
                                              String reason,
                                              LocalDateTime createdDate,
                                              String createdBy,
                                              LocalDateTime lastModifiedDate,
                                              String lastModifiedBy) {
}
