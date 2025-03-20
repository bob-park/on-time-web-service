package com.malgn.ontime.domain.document.model;

import java.time.LocalDate;

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
    private LocalDate startDateTo;
}
