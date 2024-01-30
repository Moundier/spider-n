package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.models.Project;
import com.example.utils.TimeFormatted;
import com.example.utils.Todo;

public class Spider {

  // TODOs: Copy freely, Iterate downwards using ID-based URLs.

  private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";
  private static String onStart;

  static {
    Spider.onStart = TimeFormatted.now();
    Todo.time("SOUP: Program started on " +  TimeFormatted.now());
  }

  private static void onClose(Runnable runnable) {
    Runtime.getRuntime().addShutdownHook(new Thread(runnable));
  }

  public static void main(String... args) {
    onClose(() ->  {System.out.println(); Todo.look("Program finished... " + onStart);});
    Spider.crawl(74584); // 74584 100000
  }

  private static boolean foundForbidden(Document doc, String url) {
    Boolean confidential = doc
        .getElementsByClass("label pill error")
        .text()
        .equals("Este é um projeto confidencial");

    if (confidential) {
      Todo.fail("Confidential Project: " + url);
      return true;
    }

    return false;
  }

  private static void crawl(int startId) {

    for (int numb = startId; numb > 0; numb--) {
      System.out.println("-".repeat((10 * 10 * 10) / 5));

      String url = String.format(URL_TEMPLATE, numb);
      Document doc = request(url);

      Todo.info("Found PageId: " + numb);

      if (doc == null) {
        continue;
      }

      if (foundForbidden(doc, url)) {
        continue; // TODOs: ignores and continue
      }

      Todo.done("Visiting URL: " + url + ", Title: " + doc.title());

      Project project = Project.builder()
      .id(1L)
      .logo("logo.png")
      .title("Project Title")
      .numberUnique(42)
      .classification(Project.Classification.EXTENSAO)
      .summary("Project summary")
      .objectives("Project objectives")
      .justification("Project justification")
      .results(Optional.of("Project results"))
      .dateStart(LocalDate.now())
      .dateFinal(LocalDate.now().plusDays(30))
      .publicationDate(LocalDate.now().plusDays(15))
      .concluded(false)
      .keywords(Set.of(/* set of Keyword objects */))
      .build();

      // TODOs: get set of associate
      // TODOs: associate associates with projects

      Todo.dark(TimeFormatted.now());

      // Elements title = doc.getElementsByClass("panel-title");
      // Spider.getTitles(title);
    }

    System.out.println("Crawling completed.");
  }

  // private static Set<String> getTitles(Elements elements) {
  //   Set<String> elementSet = new HashSet<String>();

  //   for (var el : elements)
  //     elementSet.add(el.text());

  //   List<String> list = List.of(
  //       "Cidades de atuação",
  //       "Plano de Trabalho",
  //       "Público alvo",
  //       "Órgãos",
  //       "Classificações",
  //       "Inovação e gestão financeira",
  //       "Dados Básicos",
  //       "Participantes");

  //   System.out.println("---------------------------------");
  //   for (var el : list) {
  //     if (!elementSet.contains(el)) {
  //       Todo.fail(String.format("Attribute { %s } NOT FOUND  ", el));
  //     } else {
  //       Todo.done(String.format("Attribute { %s } GOT FOUND ", el));
  //     }
  //   }

  //   return elementSet;
  // }

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
      Todo.fail("Document is null");
      return null;
    } catch (HttpStatusException e) {
      Todo.fail("URL path is invalid: " + e.getUrl());
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}