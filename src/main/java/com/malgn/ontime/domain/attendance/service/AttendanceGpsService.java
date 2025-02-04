package com.malgn.ontime.domain.attendance.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.attendance.feign.AttendanceFeignClient;
import com.malgn.ontime.domain.attendance.model.AttendanceGpsResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceGpsService {
    private final AttendanceFeignClient attendanceClient;

    public List<AttendanceGpsResponse> getAll() {
        return attendanceClient.getAllGps();
    }

}
