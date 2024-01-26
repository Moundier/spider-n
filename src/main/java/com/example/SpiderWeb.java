package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SpiderWeb {

  private static final String URL_TEMPLATE = "https://ead06.proj.ufsm.br/user/profile.php?id=%d";

  public static void main(String... args) {
    Note.warn("Spider: Starting to crawl");
    crawlDescendingIds(274); // 74584 100000
  }

  private static void crawlDescendingIds(int numb) {
    System.out.println("-------------------------");
    String url = String.format(URL_TEMPLATE, numb);

    // Set Chrome to run in headless mode
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");

    WebDriver driver = new ChromeDriver(options);
    driver.get(url);

    // TODOs: Credentials
    WebElement usernameInput = driver.findElement(By.id("username"));
    WebElement passwordInput = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("loginbtn"));

    usernameInput.sendKeys("202110414");
    passwordInput.sendKeys("5251251225202530254225562572259025bttaZ@");
    loginButton.click(); /// /// /// /

    Note.info("Found PageId: " + numb); // TODOs:

    if (driver.getTitle().isEmpty()) {
      driver.quit();
      crawlDescendingIds(++numb);
      return;
    }

    if (driver.getTitle().substring(0, 10).equals("Presencial")) {
      Note.fail("Visiting URL: " + url + ", Title: " + driver.getTitle());
      driver.quit();
      crawlDescendingIds(++numb);
      return;
    }

    if (driver.getTitle().toLowerCase().substring(0,24).equals("eduarda dal molin marodim")) {
      Note.warn("Found: " + url + ", Title: " + driver.getTitle());
      driver.quit();
      return;
    }

    Note.done("Visiting URL: " + url + ", Title: " + driver.getTitle().toLowerCase());

    driver.quit();

    while (true) {
      crawlDescendingIds(++numb);
    }
  }

}
