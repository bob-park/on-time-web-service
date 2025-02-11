package com.malgn.ontime.domain.attendance.model;

import java.time.LocalDate;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateAttendanceScheduleRequest(String userUniqueId,
                                              String dayOffType,
                                              LocalDate workingDate) {
}
