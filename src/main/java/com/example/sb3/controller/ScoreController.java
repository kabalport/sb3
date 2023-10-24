package com.example.sb3.controller;


import com.example.sb3.dto.request.SaveExamScoreRequest;
import com.example.sb3.dto.response.ExamFailStudentResponse;
import com.example.sb3.dto.response.ExamPassStudentResponse;
import com.example.sb3.service.StudentScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScoreController {

  private final StudentScoreService studentScoreService;

  @PutMapping("/exam/{exam}/score")
  public void save(@PathVariable("exam") String exam, @RequestBody SaveExamScoreRequest request) {

    System.out.println(request.getStudentName());
    System.out.println(request.getKorScore());
    System.out.println(request.getEnglishScore());
    System.out.println(request.getMathScore());
    System.out.println(exam);
    studentScoreService.saveScore(
        request.getStudentName(),
        exam,
        request.getKorScore(),
        request.getEnglishScore(),
        request.getMathScore());
  }

  @GetMapping("/exam/{exam}/pass")
  public List<ExamPassStudentResponse> pass(@PathVariable("exam") String exam) {
    return studentScoreService.getPassStudentList(exam);
  }

  @GetMapping("/exam/{exam}/fail")
  public List<ExamFailStudentResponse> fail(@PathVariable("exam") String exam) {
    return studentScoreService.getFailStudentList(exam);
  }
}
