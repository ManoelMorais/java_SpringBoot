package com.rocketseat.projeto_rocket.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.projeto_rocket.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.projeto_rocket.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCasa {
  
  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public List<CertificationStudentEntity> execut(){
    var result = this.certificationStudentRepository.findTop10ByOnderGradeDesc();
    return result;
  }
}
