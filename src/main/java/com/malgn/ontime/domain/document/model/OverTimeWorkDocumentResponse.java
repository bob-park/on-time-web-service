package com.malgn.ontime.domain.document.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class OverTimeWorkDocumentResponse extends DocumentResponse {

    private List<OverTimeWorkTimeResponse> workTimes;

}
