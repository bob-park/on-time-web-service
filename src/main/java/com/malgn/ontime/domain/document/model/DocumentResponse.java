package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DocumentResponse {

    private Long id;
    private String type;
    private String status;
    private String userUniqueId;
    private List<DocumentApprovalHistoryResponse> approvalHistories;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;

}
