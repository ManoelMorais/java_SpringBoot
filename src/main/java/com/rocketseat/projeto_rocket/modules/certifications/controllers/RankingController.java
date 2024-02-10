package com.rocketseat.projeto_rocket.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.projeto_rocket.modules.certifications.useCases.Top10RankingUseCasa;
import com.rocketseat.projeto_rocket.modules.students.entities.CertificationStudentEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    
  @Autowired
  private Top10RankingUseCasa top10RankingUseCasa;

  @GetMapping("/top10")
  public List<CertificationStudentEntity> top10() {
    return this.top10RankingUseCasa.execut();
  }
}
