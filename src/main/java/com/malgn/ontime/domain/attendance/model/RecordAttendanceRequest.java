package com.malgn.ontime.domain.attendance.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record RecordAttendanceRequest(String checkId,
                                      String userUniqueId) {
}
