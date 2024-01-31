package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Builder
@ToString
@Table(name = "associate")
public class Members {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long associadoId;
  private String nome;
  private String matricula; // UniqueIdentifier
  private MemberRole memberRole;
  private String email;

  @Enumerated(EnumType.STRING)
  private AcademicRole academicRole;

  private enum AcademicRole {
    DOCENTE, // Faculty
    DISCENTE, // Student
    ADMINISTRATIVO // Administrative staff
  }

  public enum MemberRole {
    AUTOR, // Author
    PARTICIPANTE, // Participant
    COAUTOR, // Co-author
    COORDENADOR, // Coordinator
    PESQUISADOR, // Researcher
    PALESTRANTE, // Speaker
    ORGANIZADOR // Event organizer
  }
}
