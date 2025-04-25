package com.malgn.ontime.domain.document.model;

import java.util.List;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateOvertimeDocumentRequest(String userUniqueId,
                                            List<OvertimeWorkTimeRequest> times) {
}
