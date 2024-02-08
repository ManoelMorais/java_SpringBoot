package com.rocketseat.projeto_rocket.modules.students.entities;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certification_student")
public class CertificationStudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 100)
  private String technology;
  
  @Column(length = 10)
  private int grate;

  @JoinColumn(name = "students_id")
  private UUID studentsID;

  @ManyToOne
  @JoinColumn(name = "students_id", insertable = false, updatable = false)
  private StudenEntity StudenEntity;

  @OneToMany
  @JoinColumn(name = "answers_certification_id",  insertable = false, updatable = false)
  List<AnswersCertificationEntity> answersCertificationEntity;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
