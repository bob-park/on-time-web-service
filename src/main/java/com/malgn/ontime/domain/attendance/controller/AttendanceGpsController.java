package com.malgn.ontime.domain.attendance.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.domain.attendance.model.AttendanceGpsResponse;
import com.malgn.ontime.domain.attendance.service.AttendanceGpsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("attendance/gps")
public class AttendanceGpsController {

    private final AttendanceGpsService attendanceGpsService;

    @GetMapping(path = "")
    public List<AttendanceGpsResponse> getAll() {
        return attendanceGpsService.getAll();
    }
}
