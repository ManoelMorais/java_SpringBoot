package com.rocketseat.projeto_rocket.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.projeto_rocket.modules.students.dto.VerifyhasCertificationdto;
import com.rocketseat.projeto_rocket.modules.students.userCases.VerifyIfHasCertificationUserCase;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationUserCase verifyIfHasCertificationUserCase;
  
  @PostMapping("/verifyIfHasCertification")
  public String VerifyIfHasCertification(@RequestBody VerifyhasCertificationdto VerifyhasCertificationdto) {

    var result = this.verifyIfHasCertificationUserCase.execute(VerifyhasCertificationdto);
    if(result){
      return "Usuário não está liberado";
    }
    return "Usuário está liberado";
  }
}
