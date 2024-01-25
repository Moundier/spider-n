package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

public class NPLEscavator {

  public static void main(String[] args) {
    
    try {

      InputStream posModelStream = new FileInputStream("en-pos-maxent.bin"); // portuguese
      POSModel posModel = new POSModel(posModelStream);
      POSTaggerME posTagger = new POSTaggerME(posModel);

      // Sample text
      String en_text = """
          In the vast and enchanting expanse of the cosmos, where myriad galaxies spiral gracefully through the cosmic dance, countless stars twinkle like celestial diamonds, casting their ethereal glow upon planets adorned with diverse landscapes, from majestic mountains crowned with glistening snow to sprawling meadows where vibrant wildflowers sway in the gentle embrace of the cosmic breeze, and amidst this cosmic symphony, life unfolds in intricate patterns, weaving tales of resilience, love, and exploration across the tapestry of existence, echoing the timeless whispers of the universe that resonate through the corridors of time and space, inviting contemplation and awe in the hearts of sentient beings traversing the cosmic journey.
          """;

      Set<String> keywords = new HashSet<>();

      // Tokenize the text
      Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
      String[] tokens = tokenizer.tokenize(en_text);

      // Tag the tokens with part-of-speech
      String[] tags = posTagger.tag(tokens);

      // Extract keywords based on POS tags
      for (int i = 0; i < tokens.length; i++) {
        // You can customize the condition based on POS tags for keyword extraction
        if (tags[i].startsWith("NN") || tags[i].startsWith("VB")) {
          // System.out.println(tokens[i]);
          keywords.add(tokens[i]);
        }

        keywords.add(tokens[i]);
      }

      // Set of keywords collected
      keywords.forEach(System.out::println);
      System.out.println("\n" + keywords);

    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
