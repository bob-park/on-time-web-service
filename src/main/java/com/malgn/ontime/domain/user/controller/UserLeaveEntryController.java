package com.malgn.ontime.domain.user.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.domain.user.model.SearchUserLeaveEntryRequest;
import com.malgn.ontime.domain.user.model.UserResponse;
import com.malgn.ontime.domain.user.service.UserLeaveEntryService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "users/leave/entries")
public class UserLeaveEntryController {

    private final UserLeaveEntryService userLeaveEntryService;

    @GetMapping(path = "")
    public List<UserResponse> getAll(SearchUserLeaveEntryRequest searchRequest) {
        return userLeaveEntryService.getAll(searchRequest);
    }

}
