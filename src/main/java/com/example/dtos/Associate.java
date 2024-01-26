package com.example.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "associate")
public class Associate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long associadoId;
  private String nome;
  private String matricula; // UniqueIdentifier
  private AssociateRole associateRole;
  private String email;

  @Enumerated(EnumType.STRING)
  private AcademicRole academicRole;

  private enum AcademicRole {
    DOCENTE, // Faculty
    DISCENTE, // Student
    ADMINISTRATIVO // Administrative staff
  }

  public enum AssociateRole {
    AUTOR, // Author
    PARTICIPANTE, // Participant
    COAUTOR, // Co-author
    COORDENADOR, // Coordinator
    PESQUISADOR, // Researcher
    PALESTRANTE, // Speaker
    ORGANIZADOR // Event organizer
  }
}
