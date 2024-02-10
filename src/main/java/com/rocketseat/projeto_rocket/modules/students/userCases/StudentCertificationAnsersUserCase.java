package com.rocketseat.projeto_rocket.modules.students.userCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.projeto_rocket.modules.questions.entities.QuestionEntity;
import com.rocketseat.projeto_rocket.modules.questions.repositories.QuestionRepository;
import com.rocketseat.projeto_rocket.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.projeto_rocket.modules.students.dto.VerifyhasCertificationdto;
import com.rocketseat.projeto_rocket.modules.students.entities.AnswersCertificationEntity;
import com.rocketseat.projeto_rocket.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.projeto_rocket.modules.students.entities.StudenEntity;
import com.rocketseat.projeto_rocket.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.projeto_rocket.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnsersUserCase {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private VerifyIfHasCertificationUserCase verifyIfHasCertificationUserCase;

  public CertificationStudentEntity execute(StudentCertificationAnswerDTO DTO) throws Exception {

    var hasCertification = this.verifyIfHasCertificationUserCase.execute(new VerifyhasCertificationdto(DTO.getEmail(), DTO.getTechnology()));

    if(hasCertification) {
      throw new Exception("Usuário já possui certificação");
    }

    List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(DTO.getTechnology());
    List<AnswersCertificationEntity> answersCertification = new ArrayList<>();

    AtomicInteger correctAnswers = new AtomicInteger(0);

    DTO.getQuestionsAnswers()
        .stream().forEach(questionAnswer -> {
          var Question = questionsEntity.stream()
              .filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
              .findFirst().get();

          var findCorrectAlternative = Question.getAlternatives().stream()
              .filter(alternative -> alternative.isCorrect()).findFirst().get();

          if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
            questionAnswer.setCorrect(true);
            correctAnswers.incrementAndGet();
          } else {
            questionAnswer.setCorrect(false);
          }

          var answersCertificationEntity = AnswersCertificationEntity.builder()
              .answerID(questionAnswer.getAlternativeID())
              .questionID(questionAnswer.getQuestionID())
              .isCorrect(questionAnswer.isCorrect()).build();

         answersCertification.add(answersCertificationEntity);
        });

    var student = studentRepository.findByEmail(DTO.getEmail());
    UUID studentId;

    if (student.isEmpty()) {
      var studentCreated = StudenEntity.builder().email(DTO.getEmail()).build();
      studentCreated = studentRepository.save(studentCreated);
      studentId = studentCreated.getId();
    } else {
      studentId = student.get().getId();
    }

    List<AnswersCertificationEntity> answersCertificationEntity = new ArrayList<>();

    CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(DTO.getTechnology())
        .studentsID(studentId)
        .grate(correctAnswers.get())
        .build();

    var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

    answersCertification.stream().forEach(aanswersCertificationEntity -> {
      aanswersCertificationEntity.setCertificationID(certificationStudentCreated.getId());
      aanswersCertificationEntity.setCertificationStudentEntity(certificationStudentCreated);
    });

    certificationStudentEntity.setAnswersCertificationEntity(answersCertificationEntity);

    certificationStudentRepository.save(certificationStudentEntity);

    return certificationStudentCreated;
  }
}
