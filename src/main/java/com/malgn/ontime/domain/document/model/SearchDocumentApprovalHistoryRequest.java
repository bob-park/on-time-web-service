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
public class SearchDocumentApprovalHistoryRequest {

    private String userUniqueId;
    private String status;
    private String type;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;

    @Builder(toBuilder = true)
    private SearchDocumentApprovalHistoryRequest(String userUniqueId, String status, String type,
        LocalDate createdDateFrom,
        LocalDate createdDateTo) {
        this.userUniqueId = userUniqueId;
        this.status = status;
        this.type = type;
        this.createdDateFrom = createdDateFrom;
        this.createdDateTo = createdDateTo;
    }
}
