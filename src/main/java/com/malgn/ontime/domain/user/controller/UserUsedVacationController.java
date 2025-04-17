package com.malgn.ontime.domain.user.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.domain.user.model.SearchUserLeaveEntryRequest;
import com.malgn.ontime.domain.user.model.UserUsedVacationResponse;
import com.malgn.ontime.domain.user.service.UserUsedVacationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("users/used/vacations")
public class UserUsedVacationController {

    private final UserUsedVacationService userUsedVacationService;

    @GetMapping(path = "")
    public List<UserUsedVacationResponse> getAll(SearchUserLeaveEntryRequest searchRequest) {
        return userUsedVacationService.getAll(searchRequest);
    }

}
