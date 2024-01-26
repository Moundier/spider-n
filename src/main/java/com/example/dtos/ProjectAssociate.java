package com.example.dtos;

import java.time.LocalDate;

import com.example.dtos.Associate.AssociateRole;

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

@Data
@Entity
@Table(name = "")
public class ProjectAssociate {

    // TODOs: LOM LearningObjectMetadata

    // aqui temos o associado ao projeto
    // podendo ser professor ou aluno
    // podendo ter uma serie de papeis 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long associadoProjetoId;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associate associado;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Project projeto;

    @Enumerated(EnumType.STRING)
    private AssociateRole associateRole;

    private String papelNoProjeto;
    private int cargaHorariaSemanal;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private boolean recebeBolsa;
}
