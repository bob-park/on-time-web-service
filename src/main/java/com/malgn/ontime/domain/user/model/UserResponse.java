package com.malgn.ontime.domain.user.model;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.auth.model.RoleResponse;
import com.malgn.ontime.domain.position.model.PositionResponse;
import com.malgn.ontime.domain.team.model.TeamResponse;

@Builder(toBuilder = true)
public record UserResponse(String uniqueId,
                           String userId,
                           String username,
                           String email,
                           String phone,
                           String cellPhone,
                           RoleResponse role,
                           PositionResponse position,
                           TeamResponse team,
                           UserLeaveEntryResponse leaveEntry,
                           LocalDateTime createdDate,
                           String createdBy,
                           LocalDateTime lastModifiedDate,
                           String lastModifiedBy,
                           Boolean isLeader,
                           String description,
                           Long proceedingDocumentsCount,
                           UserEmploymentResponse employment) {
}
