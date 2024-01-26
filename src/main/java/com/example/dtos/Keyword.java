package com.example.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "keywords")
public class Keyword {
  
  private Long keywordId;
  private String keywordUnique;
}
