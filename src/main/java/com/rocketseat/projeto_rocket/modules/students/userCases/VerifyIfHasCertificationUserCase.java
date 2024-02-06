package com.rocketseat.projeto_rocket.modules.students.userCases;

import org.springframework.stereotype.Service;

import com.rocketseat.projeto_rocket.modules.students.dto.VerifyhasCertificationdto;

@Service
public class VerifyIfHasCertificationUserCase {

  public boolean execute(VerifyhasCertificationdto dto) {
    if (dto.getEmail().equals("Manoel@gmail.com") && dto.getTechnology().equals("Java")) {
      return true;
    }
    return false;
  }
}
