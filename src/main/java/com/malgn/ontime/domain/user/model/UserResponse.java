package com.malgn.ontime.domain.user.model;

import java.time.LocalDateTime;

import lombok.Builder;

import com.malgn.auth.model.RoleResponse;

@Builder(toBuilder = true)
public record UserResponse(String uniqueId,
                           String userId,
                           String username,
                           String email,
                           String phone,
                           String cellPhone,
                           RoleResponse role,
                           LocalDateTime createdDate,
                           String createdBy,
                           LocalDateTime lastModifiedDate,
                           String lastModifiedBy) {
}
