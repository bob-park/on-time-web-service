package com.malgn.ontime.domain.document.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SearchDocumentRequest {

    private String type;
    private String status;
    private String userUniqueId;

}
