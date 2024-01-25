package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

public class OpenNLPLocationNameFinder {

  public static void main(String[] args) {
    String text = "O evento ocorrera em Sao Paulo e Rio de Janeiro ou em Santa Maria";

    List<String> locations = extractLocations(text);
    for (String location : locations) {
      System.out.println(location);
    }
  }

  public static List<String> extractLocations(String text) {
    
    try (InputStream modelIn = new FileInputStream("models/en-ner-location.bin")) {
      TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
      NameFinderME nameFinder = new NameFinderME(model);

      String[] tokens = SimpleTokenizer.INSTANCE.tokenize(text);
      Span[] spans = nameFinder.find(tokens);

      List<String> foundLocations = new ArrayList<>();
      StringBuilder location = new StringBuilder();

      for (Span span : spans) {
        for (int i = span.getStart(); i < span.getEnd(); i++) {
          location.append(tokens[i]).append(" ");
        }
        foundLocations.add(location.toString().trim());
      }

      return foundLocations;
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>(); // Handle the exception as needed
    }
  }
}
