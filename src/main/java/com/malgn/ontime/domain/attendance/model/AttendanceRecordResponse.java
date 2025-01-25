package com.malgn.ontime.domain.attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceRecordResponse(Long id,
                                       String userUniqueId,
                                       String status,
                                       String dayOffType,
                                       LocalDate workingDate,
                                       LocalDateTime clockInTime,
                                       LocalDateTime leaveWorkAt,
                                       LocalDateTime clockOutTime,
                                       String message,
                                       LocalDateTime createdDate,
                                       String createdBy,
                                       LocalDateTime lastModifiedDate,
                                       String lastModifiedBy) {
}
