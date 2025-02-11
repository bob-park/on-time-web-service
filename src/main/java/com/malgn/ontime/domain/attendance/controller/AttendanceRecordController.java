package com.malgn.ontime.domain.attendance.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.GetAttendanceRecordRequest;
import com.malgn.ontime.domain.attendance.model.RecordAttendanceRequest;
import com.malgn.ontime.domain.attendance.service.AttendanceRecordService;

@RequiredArgsConstructor
@RestController
@RequestMapping("attendance/records")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;

    @PostMapping(path = "")
    public AttendanceRecordResponse recordAttendance(@AuthenticationPrincipal OidcUser user,
        @RequestBody RecordAttendanceRequest recordRequest) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return attendanceRecordService.recordAttendance(
            recordRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build());
    }

    @GetMapping(path = "")
    public List<AttendanceRecordResponse> getAllRecords(GetAttendanceRecordRequest getRequest) {
        return attendanceRecordService.getAllRecord(getRequest);
    }
}
