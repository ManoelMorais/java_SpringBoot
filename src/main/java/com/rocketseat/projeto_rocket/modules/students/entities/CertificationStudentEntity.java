package com.rocketseat.projeto_rocket.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {

  private UUID id;
  private UUID studentsID;
  private String technology;
  private int grate;
  List<AnswersCertificationEntity> answersCertificationEntity;

}
