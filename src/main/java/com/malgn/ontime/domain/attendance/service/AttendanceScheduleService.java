package com.malgn.ontime.domain.attendance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.attendance.feign.AttendanceFeignClient;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.CreateAttendanceScheduleRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceScheduleService {

    private final AttendanceFeignClient attendanceClient;

    public AttendanceRecordResponse createSchedule(CreateAttendanceScheduleRequest createRequest) {
        return attendanceClient.createSchedule(createRequest);
    }

}
