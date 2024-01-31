package com.example.models;

import java.time.LocalDate;

import com.example.models.Members.MemberRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "")
public class ProjectMember {

    // TODOs: LOM LearningObjectMetadata
    // NOTEs: Associative table of project and associte 
    
    // NOTES: Associate is linked with Project
    // NOTES: Associate can be Professor or Student
    // NOTES: Either might play any role in the Application

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lomId;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Project projeto;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Members members;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private String papelNoProjeto;
    private int cargaHorariaSemanal;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private boolean recebeBolsa;
}

