package com.malgn.ontime.domain.document.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class VacationDocumentResponse extends DocumentResponse {

    private String vacationType;
    private String vacationSubType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal usedDays;
    private String reason;

    private List<UsedCompLeaveEntryResponse> usedCompLeaveEntries;

}
