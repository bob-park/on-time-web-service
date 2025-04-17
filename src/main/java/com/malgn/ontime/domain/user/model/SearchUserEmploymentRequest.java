package com.malgn.ontime.domain.user.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchUserEmploymentRequest {

    private String userUniqueId;
    private LocalDateTime effectiveDateFrom;
    private LocalDateTime effectiveDateTo;
    private String status;
}
