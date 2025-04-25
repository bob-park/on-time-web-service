package com.malgn.ontime.domain.document.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record OverTimeWorkTimeResponse(Long id,
                                       LocalDateTime startDate,
                                       LocalDateTime endDate,
                                       BigDecimal appliedHours,
                                       String userUniqueId,
                                       String username,
                                       String contents,
                                       Boolean isDayOff,
                                       BigDecimal appliedExtraPaymentHours,
                                       List<OverTimeWorkTimeReportResponse> reports) {
}
