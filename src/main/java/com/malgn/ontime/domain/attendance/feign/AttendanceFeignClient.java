package com.malgn.ontime.domain.attendance.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malgn.ontime.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontime.domain.attendance.model.AttendanceGpsResponse;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.CreateAttendanceScheduleRequest;
import com.malgn.ontime.domain.attendance.model.CurrentAttendanceCheckRequest;
import com.malgn.ontime.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontime.domain.attendance.model.RecordAttendanceRequest;

@FeignClient(name = "on-time-api", contextId = "on-time-api-attendance")
public interface AttendanceFeignClient {

    @PostMapping(path = "api/v1/attendance/check/current")
    AttendanceCheckResponse currentCheck(@RequestBody CurrentAttendanceCheckRequest currentRequest);

    @PostMapping(path = "api/v1/attendance/records")
    AttendanceRecordResponse recordAttendance(@RequestBody RecordAttendanceRequest recordRequest);

    @GetMapping(path = "api/v1/attendance/records")
    List<AttendanceRecordResponse> getAllRecords(@SpringQueryMap GetAttendanceRecordRequest getRequest);

    @GetMapping(path = "api/v1/attendance/gps")
    List<AttendanceGpsResponse> getAllGps();

    @PostMapping(path = "api/v1/attendance/schedules")
    AttendanceRecordResponse createSchedule(@RequestBody CreateAttendanceScheduleRequest createRequest);
}
