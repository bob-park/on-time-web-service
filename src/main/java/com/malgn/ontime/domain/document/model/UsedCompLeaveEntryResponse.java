package com.malgn.ontime.domain.document.model;

import java.math.BigDecimal;

import lombok.Builder;

import com.malgn.ontime.domain.user.model.UserCompLeaveEntryResponse;

@Builder
public record UsedCompLeaveEntryResponse(Long id,
                                         UserCompLeaveEntryResponse compLeaveEntry,
                                         BigDecimal usedDays) {
}
