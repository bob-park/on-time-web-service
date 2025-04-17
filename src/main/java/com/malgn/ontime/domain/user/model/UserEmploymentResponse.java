package com.malgn.ontime.domain.user.model;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record UserEmploymentResponse(Long id,
                                     String userUniqueId,
                                     LocalDateTime effectiveDate,
                                     String status,
                                     LocalDateTime createdDate,
                                     String createdBy,
                                     LocalDateTime lastModifiedDate,
                                     String lastModifiedBy) {

}
