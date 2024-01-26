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

    private List<String> keywords;
    private List<String> authors;
    private List<String> publishers;
    private List<String> contributors;
    private List<String> educationalObjectives;
}



