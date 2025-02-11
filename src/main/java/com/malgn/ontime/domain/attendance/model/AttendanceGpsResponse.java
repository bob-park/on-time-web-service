package com.malgn.ontime.domain.attendance.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

public record AttendanceGpsResponse(Long id,
                                    String name,
                                    String description,
                                    BigDecimal latitude,
                                    BigDecimal longitude,
                                    LocalDateTime createdDate,
                                    String createdBy,
                                    LocalDateTime lastModifiedDate,
                                    String lastModifiedBy) {

}
