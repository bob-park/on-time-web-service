package com.malgn.ontime.domain.user.model;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
public class UsedVacationResponse {

    private final int month;
    private BigDecimal used;

    @Builder
    private UsedVacationResponse(int month, BigDecimal used) {
        this.month = month;
        this.used = defaultIfNull(used, BigDecimal.ZERO);
    }

    public void add(BigDecimal usedDays) {
        this.used = getUsed().add(usedDays);
    }

}
