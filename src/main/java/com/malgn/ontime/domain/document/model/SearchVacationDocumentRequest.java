package com.malgn.ontime.domain.document.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class SearchVacationDocumentRequest {

    private String userUniqueId;
    private String status;
    private String vacationType;
    private LocalDate startDateFrom;
    private LocalDate endDateTo;

    @Builder(toBuilder = true)
    private SearchVacationDocumentRequest(String userUniqueId, String status, String vacationType,
        LocalDate startDateFrom, LocalDate endDateTo) {
        this.userUniqueId = userUniqueId;
        this.status = status;
        this.vacationType = vacationType;
        this.startDateFrom = startDateFrom;
        this.endDateTo = endDateTo;
    }
}
