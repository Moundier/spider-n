package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class OpenNLPTokenizerPortuguese {

  public static void main(String[] args) {
    try {
      POSModel posModelPt = new POSModel(new FileInputStream("models/pt-pos-maxent.bin"));
      POSTaggerME tagger = new POSTaggerME(posModelPt);
      Set<String> tokenSet = new HashSet<>();

      String pt_text = """
        O curso de Sistemas para Internet está localizado em Campus Camobi UFSM na modalidade Tecnológico Presencial sendo Integral o seu turno de funcionamento. A área de conhecimento do curso é classificada como CIÊNCIAS EXATAS E DA TERRA. Atualmente, a coordenação é de responsabilidade de JUÇARA SALETE GUBIANI e a coordenação substituta de MARCOS ALEXANDRE ROSE SILVA.
          """;

      String[] tokens = SimpleTokenizer.INSTANCE.tokenize(pt_text);
      String[] tags = tagger.tag(tokens);

      // enquanto nao for ponto, 

      for (int i = 0; i < tokens.length; i++) {
        tokenSet.add(tokens[i]);
        System.out.println("Tag: " + tags[i] + "Tokens: " + tokens[i]);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // NOTE: propTokens are proper nouns (names of places, courses and people)

  public List<String> getPlacesNames(String textNLP) {
    return List.of();
  }

  public List<String> getPeopleNames(String textNLP) {
    return List.of();
  }

  public List<String> setIgnore(String textNLP) {
    return List.of();
  }

}

// dividir entre sujeito e predicado
// remover nomes