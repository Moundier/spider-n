package com.example;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.print.Doc;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    Todo.time("SOUP: Program started on " + TimeFormatted.now());
  }

  private static void onClose(Runnable runnable) {
    Runtime.getRuntime().addShutdownHook(new Thread(runnable));
  }

  public static void main(String... args) {
    onClose(() -> {
      System.out.println("\n");
      Todo.look("Program finished... " + onStart);
      System.out.println("\n");
    });

    Thread thread = new Thread(() -> Spider.crawl(74584));
    try {
      thread.start();
      thread.join();
    } catch (InterruptedException e) {
      Todo.fail(e.getMessage());
    }
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

  // TODOs: important for investivation of potential changes
  static Set<String> inspectStatus = new HashSet<>();
  static Set<String> inspectClassification = new HashSet<>();
  static Set<String> inspectMemeberRole = new HashSet<>();

  // Add type of vinculo
  // Add Status of Vinculo
  // Add Curso
  // Add Funcao no projeto

  private static void crawl(int startId) {

    WebDriver driver = new ChromeDriver();

    for (int numb = startId; numb > 0; numb--) {

      System.out.println("-".repeat((10 * 10 * 10) / 5));

      String url = String.format(URL_TEMPLATE, numb);
      Document doc = request(url);

      Todo.info("Found PageId: " + numb);

      if (doc == null) {
        continue;
      }

      if (foundForbidden(doc, url)) {
        continue; // TODOs: ignores forbidden projects and continue
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
          null, // TODOs: publicationDate
          null,
          getStatus(doc.select("div.span6 > span").get(14).text()), // TODOs: concluded
          null // TODOs: keywords
      );

      // Element element = doc.select("tbody").first();

      // TODOs: update html document, driver.get, pass tabs, collect all, next 
      doc = Jsoup.parse(driver(url, driver)); 

      try {
        Elements elements = doc.select("tbody > tr");

        for (var el : elements) {
          // String element = el.select("td").text();

          String elA = el.select("td").get(1).text();
          String elB = el.select("td").get(2).text();
          String elC = el.select("td").get(3).text();
          String elD = el.select("td").get(4).text();
          String elE = el.select("td").get(5).text();
          Todo.warn(elA);
          Todo.warn(elB);
          Todo.warn(elC);
          Todo.warn(elD);
          Todo.warn(elE);
        }

      } catch (Exception e) {
        Todo.fail(e.getMessage());
      }
      // Set<Members> members = getMembers(doc.select("tr td"));

      System.out.println(project);

      Todo.time(TimeFormatted.now());
    }

    driver.quit();

    System.out.println("Crawling completed.");
  }

  private static String driver(String url, WebDriver driver) {
    // ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless");
    try {
      // TODOs: https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=74527      // page1_6, page2_6, page3_6, page4_6,  page5_6, page6_6
      driver.get(url);
      final long time = 3000;
      Thread.sleep(time);
      String html = driver.getPageSource();
      List<WebElement> elements = driver.findElements(By.cssSelector(".btn-group.small")); 

      for (int i = 0; i < elements.size(); ++i) {

        WebElement openButton = elements.get(i);
        System.out.println("[Button]: " + i);

        if (openButton != null) {
          Thread.sleep(time); 
          openButton.click(); // NOTE: Open Pop Up
          Thread.sleep(time); 
          List<WebElement> closeButtons = driver.findElements(By.cssSelector(".modaljs-scroll-overlay .close"));
          Thread.sleep(time);

          try {
            WebElement closeOrder = closeButtons.get(i);
            System.out.println("[Button] Clicked ");
            closeOrder.click();
            Thread.sleep(time); // NOTE: Close Pop Up
            System.out.println("[Button] Closed ");
          } catch (Exception e) {
            System.out.println("BEFORE EXCEPTION");
          }
        }
      }

      return html;
    } 
    catch (Exception e) {
      System.out.println("Error: print stack trace");
      Todo.fail(e.getMessage());
      return null;
    } 
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

  private static Set<Members> getMembers(Elements elements) {

    Set<Members> associates = new HashSet<Members>();

    for (Element el : elements) {

      // NOTE: PERSONAL
      // NOTE: vinculo
      // NOTE: vinculoStatus
      // NOTE: email

      // NOTE: GENERAL
      // NOTE: get image
      // NOTE: matricula
      // NOTE: nome
      // NOTE: funcao
      // NOTE: carga horaria
      // NOTE: periodo
      System.out.println(el.text());
    }

    return associates;
  }

  // FAIL: Unique Id 74441 Unexpectedly failed
  // FAIL: Stack Overflow at 72714 (ERROR)
  // FAIL: Unknown 73138 (ERROR)

  private static Project setProject(
      String title,
      String numberUnique,
      Classification classification,
      String summary,
      String objectives,
      String defense,
      String results,
      String dateStart,
      String dateFinal,
      String publicationDate,
      String completionDate,
      Status status,
      Set<Keyword> keywords) {

    final String VALUE_IS_ABSENT = "Information is Absent";
    summary = Optional.ofNullable(summary).orElse(VALUE_IS_ABSENT);
    objectives = Optional.ofNullable(objectives).orElse(VALUE_IS_ABSENT);
    defense = Optional.ofNullable(defense).orElse(VALUE_IS_ABSENT);
    results = Optional.ofNullable(results).orElse(VALUE_IS_ABSENT);

    return Project.builder()
        // .id(1L)
        .logo("logo.png")
        .title(null) // TODOs: title
        .numberUnique(numberUnique)
        .classification(classification) // TODOs: title
        .summary(null) // TODOs: summary
        .objectives(null) // TODOs: objectives
        .defense(null) // TODOs: justification
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
    } catch (NullPointerException e) {
      Todo.fail("Document is null");
      Todo.time(TimeFormatted.now());
      return null;
    } catch (HttpStatusException e) {
      Todo.fail("URL path is invalid: " + e.getUrl());
      Todo.time(TimeFormatted.now());
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String validHTML(String content) {
    // Wrap the content with a complete HTML document structure
    return "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>HTML Wrapper</title>\n" +
        "</head>\n" +
        "<body>\n" +
        content + "\n" +
        "</body>\n" +
        "</html>";
  }

}