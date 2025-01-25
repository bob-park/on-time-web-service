package com.malgn.ontime.domain.attendance.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.RecordAttendanceRequest;
import com.malgn.ontime.domain.attendance.service.AttendanceRecordService;

@RequiredArgsConstructor
@RestController
@RequestMapping("attendance/record")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;

    @PostMapping(path = "")
    public AttendanceRecordResponse recordAttendance(@RequestBody RecordAttendanceRequest recordRequest){
        return attendanceRecordService.recordAttendance(recordRequest);
    }
}
