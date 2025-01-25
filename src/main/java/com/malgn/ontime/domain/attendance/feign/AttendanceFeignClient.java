package com.malgn.ontime.domain.attendance.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malgn.ontime.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.CurrentAttendanceCheckRequest;
import com.malgn.ontime.domain.attendance.model.RecordAttendanceRequest;

@FeignClient(name = "on-time-api", contextId = "on-time-api-attendance")
public interface AttendanceFeignClient {

    @PostMapping(path = "api/v1/attendance/check/current")
    AttendanceCheckResponse currentCheck(@RequestBody CurrentAttendanceCheckRequest currentRequest);

    @PostMapping(path = "api/v1/attendance/record")
    AttendanceRecordResponse recordAttendance(@RequestBody RecordAttendanceRequest recordRequest);
}
