package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.malgn.ontime.domain.user.model.UserResponse;

@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DocumentResponse {

    private Long id;
    private String type;
    private String status;
    private String userUniqueId;
    private UserResponse user;
    private List<DocumentApprovalHistoryResponse> approvalHistories;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;

}
