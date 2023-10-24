package com.example.sb3.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ExamPassStudentResponse {
  private final String studentName;
  private final Double avgScore;
}
