package com.example;

public class Dog {

  // ANSI escape codes for colors
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public static void info(String message) {
    System.out.println(ANSI_BLUE + "INFO: " + message + ANSI_RESET);
  }

  public static void fail(String message) {
    System.out.println(ANSI_RED + "FAIL: " + message + ANSI_RESET);
  }

  public static void warn(String message) {
    System.out.println(ANSI_YELLOW + "WARN: " + message + ANSI_RESET);
  }

  public static void done(String message) {
    System.out.println(ANSI_GREEN + "DONE: " + message + ANSI_RESET);
  }
}
