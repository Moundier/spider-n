package com.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.utils.TimeFormatted;

public class Spider {

  // TODOs: Copy freely, Iterate downwards using ID-based URLs.

  private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";

  public static void main(String... args) {
    Runnable runnable = () -> {
      System.out.println("\nProgram finished... " + TimeFormatted.now());
    };

    Runtime.getRuntime().addShutdownHook(new Thread(runnable));

    Note.warn("Spider: Starting to crawl");
    Spider.crawl(74584); // 74584 100000
  }

  private static void crawl(int startId) {
    
    for (int numb = startId; numb > 0; numb--) {
      System.out.println("-------------------------");
      String url = String.format(URL_TEMPLATE, numb);
      Document doc = request(url);

      Note.info("Found PageId: " + numb);

      if (doc == null) {
        Note.fail("Failed to retrieve document for URL: " + url);
        continue;
      }

      Boolean isProjectConfidential = doc
          .getElementsByClass("label pill error")
          .text()
          .equals("Este é um projeto confidencial");

      if (isProjectConfidential) {
        Note.fail("Confidential Project: " + url);
        continue;
      }

      Note.done("Visiting URL: " + url + ", Title: " + doc.title());

      Elements title = doc.getElementsByClass("panel-title");
      Set<String> titleSet = Spider.titles(title, doc);
      System.out.println(titleSet);
    }

    System.out.println("Crawling completed.");
  }

  private static Set<String> titles(Elements elements, Document document) {
    Set<String> elementSet = new HashSet<>();

    for (var el : elements) {
      elementSet.add(el.text());
    }

    List<String> list = List.of(
        "Cidades de atuação",
        "Plano de Trabalho",
        "Público alvo",
        "Órgãos",
        "Classificações",
        "Inovação e gestão financeira",
        "Dados Básicos",
        "Participantes");

    System.out.println("---------------------------------");
    for (var el : list) {
      if (!elementSet.contains(el)) {
        Note.fail(String.format("Not found { %s } ", el));
      } else {
        Note.done(String.format("Got found { %s } ", el));
      }
    }
    System.out.println("---------------------------------");

    return elementSet;
  }

  // TODOs: to be used
  // private static void getProjectBasic(Elements elements, Document doc) {
  // for (Element el : elements) {
  // System.out.println(el.child(doc.getElementsByClass("span12").size() %
  // 2).text().toUpperCase()/* .text() */ );
  // System.out.println(el.child(doc.getElementsByClass("span12").size() % 2 +
  // 2).text()/* .text() */ + "\n");
  // }
  // }

  // FAIL: Unique Id 74441 Unexpectedly failed
  // FAIL: Stack Overflow at 72714 (ERROR)
  // FAIL: Unknown 73138 (ERROR)

  private static Document request(String url) {
    try {
      Connection connection = Jsoup.connect(url);
      Connection.Response response = connection.execute();
      Document document = response.parse();
      return document;
    } catch (NullPointerException e) {
      Note.fail("Document is null");
      return null;
    } catch (HttpStatusException e) {
      Note.fail("URL path is invalid: " + e.getUrl());
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}