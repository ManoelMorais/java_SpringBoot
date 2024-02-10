package com.rocketseat.projeto_rocket.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.projeto_rocket.modules.students.entities.StudenEntity;

public interface StudentRepository extends JpaRepository<StudenEntity, UUID> {

  public Optional<StudenEntity> findByEmail(String email);
}
