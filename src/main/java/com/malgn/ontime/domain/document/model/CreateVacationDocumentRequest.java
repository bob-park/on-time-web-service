package com.malgn.ontime.domain.document.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateVacationDocumentRequest(String userUniqueId,
                                            String vacationType,
                                            String vacationSubType,
                                            LocalDate startDate,
                                            LocalDate endDate,
                                            String reason,
                                            List<UsedCompLeaveEntryRequest> compLeaveEntries) {
}
