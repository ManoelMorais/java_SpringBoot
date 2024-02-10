package com.rocketseat.projeto_rocket.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rocketseat.projeto_rocket.modules.students.entities.CertificationStudentEntity;

public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

  @Query("SELECT c FROM certification_student c INNER JOIN c.StudenEntity s WHERE s.email = :email AND c.technology = :technology")
  List<CertificationStudentEntity> findByEmailAndTechnology(String email, String technology);

  @Query("SELECT c FROM certification_student c ORDER BY c.grate DESC LIMIT 10")
  List<CertificationStudentEntity> findTop10ByOnderGradeDesc();
}
