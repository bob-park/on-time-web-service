package com.malgn.ontime.domain.user.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import com.malgn.common.exception.NotFoundException;
import com.malgn.ontime.domain.user.feign.UserEmploymentFeignClient;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserLeaveEntryFeignClient;
import com.malgn.ontime.domain.user.model.SearchUserEmploymentRequest;
import com.malgn.ontime.domain.user.model.SearchUserLeaveEntryRequest;
import com.malgn.ontime.domain.user.model.UserEmploymentResponse;
import com.malgn.ontime.domain.user.model.UserLeaveEntryResponse;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLeaveEntryService {

    private final UserFeignClient userClient;
    private final UserEmploymentFeignClient userEmploymentClient;
    private final UserLeaveEntryFeignClient userLeaveEntryClient;

    public List<UserResponse> getAll(SearchUserLeaveEntryRequest searchRequest) {

        Page<UserEmploymentResponse> result =
            userEmploymentClient.search(
                SearchUserEmploymentRequest.builder()
                    .status("ACTIVE")
                    .build(),
                PageRequest.of(0, 100)).toPage();

        List<UserEmploymentResponse> userEmployments = result.getContent();

        List<UserResponse> users = Lists.newArrayList();

        for (UserEmploymentResponse userEmployment : userEmployments) {
            UserResponse user = userClient.getById(userEmployment.userUniqueId());

            try {
                UserLeaveEntryResponse leaveEntry =
                    userLeaveEntryClient.getLeaveEntry(userEmployment.userUniqueId(), searchRequest.year());

                users.add(
                    user.toBuilder()
                        .leaveEntry(leaveEntry)
                        .employment(userEmployment)
                        .build());
            } catch (NotFoundException e) {
                log.debug("User leave entry not found: {}", e.getMessage());
            }

        }

        return users;
    }

}
