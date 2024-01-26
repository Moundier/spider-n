package com.example.dtos;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Professor {
  
  private String name;
  private String description;
  private String pictureURL;
  private ArrayList<Keyword> keywords;


  // 
  // professor possui lista de disciplina
  // professor possui lista de curso
  // professor possui lista de keyword
  // professor possui lista de projeto

  // TODOs: porem nao pode ser professor, vmaos dividir em um enum de titulos por funcao
  // TODOs: examplo, coordenado, autor, professor, 



  // DOS dados basicos, pegar classificacao principal
  // dos participantes, as funcoes

  public enum ProjectCategory {
    PESQUISA,
    EXTENSAO,
    MESTRADO,
    DOUTORADO
  }

}

// Nesse projeto x o professor y desempenha papel z

// coleta entidade
// se entidade nao existir, salva
// gera keywords de alguns atributos da entidade
// foreach keyword, se keyword nao existir, salve
// salva associacao keywords-entidade

// mas e se professores quiserem se tornar usuarios? 
// professor possui tags
// professor coordena projetos
// professor pertence a cursos
// professor ensina disciplinas



