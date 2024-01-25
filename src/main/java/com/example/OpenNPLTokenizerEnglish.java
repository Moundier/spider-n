package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

public class OpenNPLTokenizerEnglish {

  public static void main(String[] args) {
    
    try {

      POSModel posModel = new POSModel(new FileInputStream("models/en-pos-maxent.bin")); // Brazilian Portuguese
      POSTaggerME posTagger = new POSTaggerME(posModel);

      String en_text = """
          In the vast and enchanting expanse of the cosmos, where myriad galaxies spiral gracefully through the cosmic dance, countless stars twinkle like celestial diamonds, casting their ethereal glow upon planets adorned with diverse landscapes, from majestic mountains crowned with glistening snow to sprawling meadows where vibrant wildflowers sway in the gentle embrace of the cosmic breeze, and amidst this cosmic symphony, life unfolds in intricate patterns, weaving tales of resilience, love, and exploration across the tapestry of existence, echoing the timeless whispers of the universe that resonate through the corridors of time and space, inviting contemplation and awe in the hearts of sentient beings traversing the cosmic journey.
          """;

      Set<String> keywords = new HashSet<>();

      Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
      String[] tokens = tokenizer.tokenize(en_text);
      String[] tags = posTagger.tag(tokens); // Tag the tokens with part-of-speech

      for (int i = 0; i < tokens.length; i++) {
        if (tags[i].startsWith("NN") || tags[i].startsWith("VB")) {
          keywords.add(tokens[i]);
        }
        keywords.add(tokens[i]);
      }

      // TODOs: Set of keywords collected
      keywords.forEach(System.out::println);
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
