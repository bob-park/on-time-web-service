package com.malgn.ontime.domain.document.model;

import java.time.LocalDateTime;

public record OvertimeWorkTimeRequest(String userUniqueId,
                                      String username,
                                      String contents,
                                      LocalDateTime startDate,
                                      LocalDateTime endDate,
                                      Boolean isDayOff) {
}
