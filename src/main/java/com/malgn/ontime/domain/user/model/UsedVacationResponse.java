package com.malgn.ontime.domain.user.model;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
public class UsedVacationResponse {

    private final int month;
    private BigDecimal used;
    private BigDecimal usedComp;

    @Builder
    private UsedVacationResponse(int month, BigDecimal used, BigDecimal usedComp) {
        this.month = month;
        this.used = getIfNull(used, BigDecimal.ZERO);
        this.usedComp = getIfNull(usedComp, BigDecimal.ZERO);
    }

    public void add(BigDecimal usedDays) {
        this.used = getUsed().add(usedDays);
    }

    public void addComp(BigDecimal usedDays) {
        this.usedComp = getUsedComp().add(usedDays);
    }

}
