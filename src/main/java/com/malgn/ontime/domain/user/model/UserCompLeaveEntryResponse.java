package com.malgn.ontime.domain.user.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserCompLeaveEntryResponse(Long id,
                                         String contents,
                                         LocalDate effectiveDate,
                                         BigDecimal leaveDays,
                                         BigDecimal usedDays,
                                         LocalDateTime createdDate,
                                         String createdBy,
                                         LocalDateTime lastModifiedDate,
                                         String lastModifiedBy) {
}
