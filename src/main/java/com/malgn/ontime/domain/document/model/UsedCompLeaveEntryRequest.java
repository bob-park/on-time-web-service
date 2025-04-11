package com.malgn.ontime.domain.document.model;

import java.math.BigDecimal;

public record UsedCompLeaveEntryRequest(Long compLeaveEntryId,
                                        BigDecimal usedDays) {
}
