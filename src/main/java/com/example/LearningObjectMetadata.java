package com.example;

import java.util.List;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;

@Data
@Builder
@ToString
public class LearningObjectMetadata {
    
    // TODOs: LOM

    private String title;
    private String description;
    private String language;
    private String format;
    private String location;
    private String rights;
    private String agregationLevel;
    private String interactivityType;

    // TODOs: participants should have name and type
    // TODOs: collect types 

    private List<String> keywords;
    private List<String> authors;
    private List<String> publishers;
    private List<String> contributors; // participantes com enum
    private List<String> educationalObjectives;

    // Participante pode ser professor ou qualquer outra categoria

    // TODOs: Find Hyperlink With Regex 
}


// LISTA DE CAMPUS
// JOSE_MARIANO_ROCHA_FILHO_SEDE
// FREDERICO_WESTPHALEN
// PALMEIRA DAS MISSOES
// CACHOEIRA_DO_SUL
// SUBSTRING CTRL F5 Boyer-Moore ou o algoritmo de Knuth-Morris-Pratt.


