package com.malgn.ontime.domain.attendance.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontime.domain.attendance.model.CurrentAttendanceCheckRequest;
import com.malgn.ontime.domain.attendance.service.AttendanceCheckService;

@RequiredArgsConstructor
@RestController
@RequestMapping("attendance/check")
public class AttendanceCheckController {

    private final AttendanceCheckService attendanceCheckService;

    @PostMapping(path = "current")
    public AttendanceCheckResponse currentCheck(@RequestBody CurrentAttendanceCheckRequest currentRequest) {
        return attendanceCheckService.currentCheck(currentRequest);
    }
}
