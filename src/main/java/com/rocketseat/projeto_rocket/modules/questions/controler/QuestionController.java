package com.rocketseat.projeto_rocket.modules.questions.controler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rocketseat.projeto_rocket.modules.questions.dto.AlternativeResultDTO;
import com.rocketseat.projeto_rocket.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.projeto_rocket.modules.questions.entities.AlternativeEntity;
import com.rocketseat.projeto_rocket.modules.questions.entities.QuestionEntity;
import com.rocketseat.projeto_rocket.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("/technology/{technology}")
  public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
    System.out.println("TECH ===" + technology);
    var result = this.questionRepository.findByTechnology(technology);

    var toMap = result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());
    return toMap;
  }

  static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
    var questionResultDTO = QuestionResultDTO.builder()
        .id(question.getId())
        .tecnology(question.getTechnology())
        .description(question.getDescription()).build();

    List<AlternativeResultDTO> alternativesResultDTOs = question.getAlternatives().stream()
        .map(alternative -> mapAlternativeResultDTO(alternative)).collect(Collectors.toList());

    questionResultDTO.setAlternatives(alternativesResultDTOs);
    return questionResultDTO;
  };

  static AlternativeResultDTO mapAlternativeResultDTO(AlternativeEntity alternativeResultDTO) {
    return AlternativeResultDTO.builder().id(alternativeResultDTO.getId())
        .description(alternativeResultDTO.getDescription()).build();
  }
}
