package com.rocketseat.projeto_rocket.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rocketseat.projeto_rocket.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.projeto_rocket.modules.students.dto.VerifyhasCertificationdto;
//import com.rocketseat.projeto_rocket.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.projeto_rocket.modules.students.userCases.StudentCertificationAnsersUserCase;
import com.rocketseat.projeto_rocket.modules.students.userCases.VerifyIfHasCertificationUserCase;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationUserCase verifyIfHasCertificationUserCase;

  @Autowired
  private StudentCertificationAnsersUserCase studentCertificationAnswerUserCase;

  @PostMapping("/verifyIfHasCertification")
  public String VerifyIfHasCertification(@RequestBody VerifyhasCertificationdto VerifyhasCertificationdto) {

    var result = this.verifyIfHasCertificationUserCase.execute(VerifyhasCertificationdto);
    if (result) {
      return "Usuário não está liberado";
    }
    return "Usuário está liberado";
  }

  @PostMapping("/certificantion/answer")
  public ResponseEntity<Object> certicationAnswer(
      @RequestBody StudentCertificationAnswerDTO DTO) {
    try {
      var result = studentCertificationAnswerUserCase.execute(DTO);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
