package com.example.sb3.service;


import com.example.sb3.calcaulator.MyCalculator;
import com.example.sb3.dto.response.ExamFailStudentResponse;
import com.example.sb3.dto.response.ExamPassStudentResponse;
import com.example.sb3.model.StudentFail;
import com.example.sb3.model.StudentPass;
import com.example.sb3.model.StudentScore;
import com.example.sb3.repository.StudentFailRepository;
import com.example.sb3.repository.StudentPassRepository;
import com.example.sb3.repository.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentScoreService {
  private final StudentScoreRepository studentScoreRepository;
  private final StudentPassRepository studentPassRepository;
  private final StudentFailRepository studentFailRepository;

  public void saveScore(
      String studentName, String exam, Integer korScore, Integer englishScore, Integer mathScore) {
    StudentScore studentScore =
        StudentScore.builder()
            .exam(exam)
            .studentName(studentName)
            .korScore(korScore)
            .englishScore(englishScore)
            .mathScore(mathScore)
            .build();

    System.out.println(korScore);
    studentScoreRepository.save(studentScore);

    MyCalculator myCalculator = new MyCalculator(0.0);
    Double avgScore =
        myCalculator
            .add(korScore.doubleValue())
            .add(englishScore.doubleValue())
            .add(mathScore.doubleValue())
            .divide(3.0)
            .getResult();
    if (avgScore >= 60) {
      StudentPass studentPass =
          StudentPass.builder().exam(exam).studentName(studentName).avgScore(avgScore).build();
      studentPassRepository.save(studentPass);
    } else {
      StudentFail studentFail =
          StudentFail.builder().exam(exam).studentName(studentName).avgScore(avgScore).build();
      studentFailRepository.save(studentFail);
    }
  }

  public List<ExamPassStudentResponse> getPassStudentList(String exam) {
    List<StudentPass> studentPasses = studentPassRepository.findAll();

    return studentPasses.stream()
        .filter((pass) -> pass.getExam().equals(exam))
        .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
        .toList();
  }

  public List<ExamFailStudentResponse> getFailStudentList(String exam) {
    List<StudentFail> studentFails = studentFailRepository.findAll();

    return studentFails.stream()
        .filter((pass) -> pass.getExam().equals(exam))
        .map((pass) -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore()))
        .toList();
  }
}
