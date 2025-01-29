package com.malgn.ontime.domain.attendance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.attendance.feign.AttendanceFeignClient;
import com.malgn.ontime.domain.attendance.model.AttendanceCheckResponse;
import com.malgn.ontime.domain.attendance.model.CurrentAttendanceCheckRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceCheckService {

    private final AttendanceFeignClient attendanceClient;

    public AttendanceCheckResponse currentCheck(CurrentAttendanceCheckRequest currentRequest){
        return attendanceClient.currentCheck(currentRequest);
    }

}
