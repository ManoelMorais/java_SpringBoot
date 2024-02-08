package com.rocketseat.projeto_rocket.modules.questions.dto;

import java.util.UUID;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResultDTO {

  private UUID id;
  private String tecnology;
  private String description;

  private List<AlternativeResultDTO> alternatives;
}
