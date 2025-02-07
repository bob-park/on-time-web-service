package com.malgn.ontime.domain.attendance.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.attendance.model.AttendanceRecordResponse;
import com.malgn.ontime.domain.attendance.model.CreateAttendanceScheduleRequest;
import com.malgn.ontime.domain.attendance.service.AttendanceScheduleService;

@RequiredArgsConstructor
@RestController
@RequestMapping("attendance/schedules")
public class AttendanceScheduleController {

    private final AttendanceScheduleService attendanceScheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public AttendanceRecordResponse createSchedule(@AuthenticationPrincipal OidcUser user,
        @RequestBody CreateAttendanceScheduleRequest createRequest) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return attendanceScheduleService.createSchedule(
            createRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build());
    }

}
