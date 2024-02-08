package com.rocketseat.projeto_rocket.modules.students.userCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.projeto_rocket.modules.students.dto.VerifyhasCertificationdto;
import com.rocketseat.projeto_rocket.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUserCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public boolean execute(VerifyhasCertificationdto dto) {
    var result = this.certificationStudentRepository.findByEmailAndTechnology(dto.getEmail(), dto.getTechnology());
    if (!result.isEmpty()) {
      return true;
    }
    return false;
  }
}
