package com.example.dtos;

public class Associate {

  /**
  ** Represents the academic role of an associate.
  **/
  public enum AcademicRole {
    DOCENTE, // Faculty
    DISCENTE, // Student
    ADMINISTRATIVO // Administrative staff
  }

  /**
   * Represents the specific role of an associate within the campus context.
   */
  public enum AssociateRole {
    AUTOR, // Author
    PARTICIPANTE, // Participant
    COAUTOR, // Co-author
    COORDENADOR, // Coordinator
    PESQUISADOR, // Researcher
    PALESTRANTE, // Speaker
    ORGANIZADOR // Event organizer
  }

  // Entao colleta os associados
  // Entao colleta o projeto
  // Vincula projeto com associados
}
