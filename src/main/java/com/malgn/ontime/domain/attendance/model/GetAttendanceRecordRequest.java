package com.malgn.ontime.domain.attendance.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class GetAttendanceRecordRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String userUniqueId;
    private String status;
    private String dayOffType;
}
