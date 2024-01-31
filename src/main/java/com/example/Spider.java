package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.models.Members;
import com.example.models.Keyword;
import com.example.models.Project;
import com.example.models.Project.Classification;
import com.example.models.Project.Status;
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
    onClose(() ->  {
      System.out.println("\n");
      Todo.look("Program finished... " + onStart);
      System.out.println("\n"); 
    });
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


  static Set<String> inspectStatus = new HashSet<>();
  static Set<String> inspectClassification = new HashSet<>();

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

      // Set<Members> members = getMembers();
      // System.out.println(members.toString());
      
      // TODOs: Options
      inspectClassification.add(doc.select("div.span6 > span").get(4).text());
      inspectStatus.add(doc.select("div.span6 > span").get(14).text());
      System.out.println(inspectClassification);
      System.out.println(inspectStatus);

      Project project = setProject(
        doc.select("div.span12 > span").get(1).text(), 
        doc.select("div.span6 > span").get(1).text(), 
        getClassfication(doc.select("div.span6 > span").get(4).text()), 
        doc.select("div.span12 > span").get(3).text(), 
        doc.select("div.span12 > span").get(5).text(), 
        doc.select("div.span12 > span").get(7).text(), 
        doc.select("div.span12 > span").get(9).text(), 
        doc.select("div.span3 > span").get(1).text(), // NOTE: dateStart
        doc.select("div.span3 > span").get(3).text(), // NOTE: dateFinal
        null,  // TODOs: publicationDate
        null,
        getStatus(doc.select("div.span6 > span").get(14).text()),  // TODOs: concluded
        null   // TODOs: keywords
      );

      System.out.println(project);

      // TODOs: Find Members
      // TODOs: Find Projects
      // TODOs: Link Members with Projects

      Todo.time(TimeFormatted.now());
    }

    System.out.println("Crawling completed.");
  }

  private static Status getStatus(String string) {
    
    switch (string) {
      case "Suspenso":
        return Status.SUSPENSO;
      case "Concluído/Publicado":
        return Status.CONCLUIDO_PUBLICADO;
      case "Cancelado":
        return Status.CANCELADO; 
      case "Em andamento":
        return Status.EM_ANDAMENTO;
      default:
        return Status.DEFAULT;
    }
  }

  private static Classification getClassfication(String string) {

    switch (string) {
      case "Ensino":
        return Classification.ENSINO;
      case "Extensão":
        return Classification.EXTENSAO;
      case "Pesquisa":
        return Classification.PESQUISA;
      case "Desenvolvimento Institucional":
        return Classification.DESENVOLVIMENTO_INSTITUCIONAL;
      default:
        return Classification.DEFAULT;
    }
  }

  private static Set<Members> getMembers() {

    Set<Members> associates = new HashSet<Members>();

    for (Members associate : associates) {
      
    }

    return associates;
  }

  // }

  // FAIL: Unique Id 74441 Unexpectedly failed
  // FAIL: Stack Overflow at 72714 (ERROR)
  // FAIL: Unknown 73138 (ERROR)

  private static Project setProject(
    String title,
    String numberUnique,
    Classification classification,
    String summary,
    String objectives,
    String justification,
    String results,
    String dateStart,
    String dateFinal,
    String publicationDate,
    String completionDate,
    Status status,
    Set<Keyword> keywords
  ) {

    final String VALUE_IS_ABSENT = "Information is Absent";
    summary = Optional.ofNullable(summary).orElse(VALUE_IS_ABSENT);
    objectives = Optional.ofNullable(objectives).orElse(VALUE_IS_ABSENT);
    justification = Optional.ofNullable(justification).orElse(VALUE_IS_ABSENT);
    results = Optional.ofNullable(results).orElse(VALUE_IS_ABSENT);

    return Project.builder()
    // .id(1L)
    .logo("logo.png")
    .title(null) // TODOs: title
    .numberUnique(numberUnique)
    .classification(classification) // TODOs: title
    .summary(null) // TODOs: summary 
    .objectives(null) // TODOs: objectives
    .justification(null) // TODOs: justification 
    .results(null) // TODOs: results
    .dateStart(LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
    .dateFinal(LocalDate.parse(dateFinal, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
    .publicationDate(null)
    .completionDate(null)
    .status(status)
    .keywords(Set.of(/* set of Keyword objects */))
    .build();
  }

  private static Document request(String url) {
    try {
      Connection connection = Jsoup.connect(url);
      Connection.Response response = connection.execute();
      Document document = response.parse();
      return document;
    } 
    catch (NullPointerException e) {
      Todo.fail("Document is null");
      Todo.time(TimeFormatted.now());
      return null;
    } 
    catch (HttpStatusException e) {
      Todo.fail("URL path is invalid: " + e.getUrl());
      Todo.time(TimeFormatted.now());
      return null;
    } 
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}