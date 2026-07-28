package com.malgn.ontime.domain.user.model;

import com.malgn.ontime.domain.team.model.TeamResponse;

public record UserGroupResponse(String id,
                                boolean isLeader,
                                String description,
                                TeamResponse group) {
}
