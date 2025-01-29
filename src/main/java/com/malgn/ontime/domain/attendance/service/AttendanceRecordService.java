package com.malgn.ontime.domain.attendance.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.attendance.feign.AttendanceFeignClient;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontime.domain.attendance.model.RecordAttendanceRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendanceRecordService {

    private final AttendanceFeignClient attendanceClient;

    public AttendanceRecordResponse recordAttendance(RecordAttendanceRequest recordRequest) {
        return attendanceClient.recordAttendance(recordRequest);
    }

    public List<AttendanceRecordResponse> getAllRecord(GetAttendanceRecordRequest getRequest) {
        return attendanceClient.getAllRecords(getRequest);
    }

}
