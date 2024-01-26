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

    private static final String URL_TEMPLATE = "https://ead06.proj.ufsm.br/user/profile.php?id=%d";
    private static final int THREAD_POOL_SIZE = 5; // Adjust as needed
    private static final AtomicInteger currentId = new AtomicInteger(274);

    public static void main(String... args) {
        Note.warn("Spider: Starting to crawl");
        crawlDescendingIds();
    }

    private static void crawlDescendingIds() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.submit(() -> crawlProfile());
        }

        // Shutdown the executor when all threads are done
        executorService.shutdown();
    }

    private static void crawlProfile() {
        while (true) {
            int currentNum = currentId.getAndIncrement();
            String url = String.format(URL_TEMPLATE, currentNum);

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

            // Consider handling thread sleep to avoid overloading the website and being blocked

            // Check if you want to stop crawling based on some condition
            if (currentNum > 100000) {
                break;
            }
        }
    }
}
