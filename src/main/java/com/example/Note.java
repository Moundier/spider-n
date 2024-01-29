package com.example;

import java.io.PrintStream;

public class Note {

  // ANSI escape codes for colors
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_GREEN = "\u001B[32m";

  private static final PrintStream io = System.out; // You can customize the output stream

  public static void info(String message) {
    io(ANSI_BLUE, "INFO", message);
  }

  public static void fail(String message) {
    io(ANSI_RED, "FAIL", message);
  }

  public static void warn(String message) {
    io(ANSI_YELLOW, "WARN", message);
  }

  public static void done(String message) {
    io(ANSI_GREEN, "DONE", message);
  }

  private static void io(String color, String prefix, String message) {
    io.println(color + prefix + " " + message + ANSI_RESET);
  }

}
