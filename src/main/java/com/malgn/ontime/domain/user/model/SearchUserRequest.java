package com.malgn.ontime.domain.user.model;

import static org.apache.commons.lang3.ObjectUtils.*;

import lombok.Data;

@Data
public class SearchUserRequest {

    private Boolean isDeleted;

    public SearchUserRequest(Boolean isDeleted) {
        this.isDeleted = defaultIfNull(isDeleted, false);
    }
}
