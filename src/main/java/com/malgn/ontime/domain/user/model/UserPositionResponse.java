package com.malgn.ontime.domain.user.model;

import com.malgn.ontime.domain.position.model.PositionResponse;

public record UserPositionResponse(String userUniqueId,
                                   PositionResponse position) {
}
