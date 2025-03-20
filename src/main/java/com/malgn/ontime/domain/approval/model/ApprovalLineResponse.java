package com.malgn.ontime.domain.approval.model;

import java.time.LocalDateTime;
import java.util.List;

public record ApprovalLineResponse(Long id,
                                   String documentType,
                                   ApprovalLineResponse parent,
                                   List<ApprovalLineResponse> children,
                                   String userUniqueId,
                                   String contents,
                                   String description,
                                   LocalDateTime createdDate,
                                   LocalDateTime lastModifiedDate) {
}
