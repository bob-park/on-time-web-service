package com.malgn.ontime.domain.attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceCheckResponse(String id,
                                      String type,
                                      String attendanceType,
                                      LocalDate workingDate,
                                      LocalDateTime expiredDate,
                                      LocalDateTime createdDate,
                                      String createdBy,
                                      LocalDateTime lastModifiedDate,
                                      String lastModifiedBy) {
}
