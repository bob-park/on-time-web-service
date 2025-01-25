package com.malgn.ontime.domain.team.model;

import java.time.LocalDateTime;
import java.util.List;

public record TeamResponse(String id,
                           String name,
                           String description,
                           List<TeamUserResponse> teamUsers,
                           LocalDateTime createdDate,
                           String createdBy,
                           LocalDateTime lastModifiedDate,
                           String lastModifiedBy) {
}
