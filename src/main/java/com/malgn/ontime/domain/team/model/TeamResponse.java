package com.malgn.ontime.domain.team.model;

import java.time.LocalDateTime;
import java.util.List;

import com.malgn.ontime.domain.user.model.UserResponse;

public record TeamResponse(Long id,
                           String name,
                           String description,
                           List<UserResponse> users,
                           LocalDateTime createdDate,
                           String createdBy,
                           LocalDateTime lastModifiedDate,
                           String lastModifiedBy,
                           Boolean isLeader,
                           String teamUserDescription) {
}
