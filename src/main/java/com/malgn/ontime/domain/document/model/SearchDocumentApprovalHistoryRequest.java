package com.malgn.ontime.domain.document.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.netflix.discovery.provider.Serializer;

@ToString
@Getter
@Serializer
@NoArgsConstructor
public class SearchDocumentApprovalHistoryRequest {

    private String userUniqueId;
    private String status;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
