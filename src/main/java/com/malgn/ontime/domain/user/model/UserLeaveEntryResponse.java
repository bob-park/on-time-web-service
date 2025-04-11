package com.malgn.ontime.domain.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserLeaveEntryResponse(Long id,
                                     String userUniqueId,
                                     Integer year,
                                     BigDecimal totalLeaveDays,
                                     BigDecimal usedLeaveDays,
                                     BigDecimal totalCompLeaveDays,
                                     BigDecimal usedCompLeaveDays,
                                     LocalDateTime createdDate,
                                     LocalDateTime lastModifiedDate) {
}
