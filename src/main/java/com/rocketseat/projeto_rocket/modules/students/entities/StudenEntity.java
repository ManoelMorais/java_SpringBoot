package com.rocketseat.projeto_rocket.modules.students.entities;

import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudenEntity {

  private UUID id;
  private String email;
  private List<CertificationStudentEntity> certificationStudentEntity;

}
