package com.malgn.ontime.domain.user.model;

import java.util.List;

import lombok.Builder;

@Builder
public record UserUsedVacationResponse(String userUniqueId,
                                       Integer year,
                                       List<UsedVacationResponse> usedVacations) {
}
