package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SpiderMan {

  private static final String SECRET_KEY_USERNAME = "202110414";
  private static final String SECRET_KEY_PASSWORD = "5251251225202530254225562572259025bttaZ@";

  private static final String URL_TEMPLATE = "https://ead06.proj.ufsm.br/user/profile.php?id=%d";
  private static final int THREAD_POOL_SIZE = 10 * Thread.activeCount(); // Adjust as needed
  private static final AtomicInteger currentId = new AtomicInteger(600);
  // 1260

  public static void main(String... args) {
    System.out.println(THREAD_POOL_SIZE);
    Note.warn("Spider: Starting to crawl");
    crawlDescendingIds();
  }

  private static void crawlDescendingIds() {
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    for (int i = 0; i < THREAD_POOL_SIZE; i++) {
      executorService.submit(() -> crawlProfile());
    }

    executorService.shutdown(); // Shutdown the executor when all threads are done
  }

  private static void crawlProfile() {
    while (true) {
      int currentNum = currentId.getAndIncrement();
      String url = String.format(URL_TEMPLATE, currentNum);

      // Set Chrome to run in headless mode
      WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
      driver.get(url);

      WebElement usernameInput = driver.findElement(By.id("username"));
      WebElement passwordInput = driver.findElement(By.id("password"));
      WebElement loginButton = driver.findElement(By.id("loginbtn"));

      usernameInput.sendKeys(SECRET_KEY_USERNAME);
      passwordInput.sendKeys(SECRET_KEY_PASSWORD);
      loginButton.click();

      Note.info("Found PageId: " + currentNum);

      if (driver.getTitle().isEmpty()) {
        driver.quit();
        continue;
      }

      if (driver.getTitle().substring(0, 10).equals("Presencial")) {
        Note.fail("Visiting URL: " + url + ", Title: " + driver.getTitle());
        driver.quit();
        continue;
      }

      if (driver.getTitle().toLowerCase().substring(0, 24).equals("eduarda dal molin marodim")) {
        Note.warn("Found: " + url + ", Title: " + driver.getTitle());
        driver.quit();
        return;
      }

      Note.done("Visiting URL: " + url + ", Title: " + driver.getTitle().toLowerCase());

      driver.quit();

      if (currentNum > 100000) {
        break;
      }
    }
  }
}
