package com.example.utils;

import java.io.PrintStream;

public class Todo {

  // ANSI escape codes for colors
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUEY = "\u001B[34m";
  public static final String ANSI_REDDY = "\u001B[31m";
  public static final String ANSI_YELLY = "\u001B[33m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_PURPY = "\u001B[35m";
  public static final String ANSI_CYANY = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_BLACK = "\u001B[30m";

  private static final PrintStream io = System.out; // TODOs: You can customize the output stream

  private static void io(String color, String prefix, String message) {
    io.println(color + prefix + " " + message + ANSI_RESET);
  }

  private static void io(String color, String message) {
    io.println(color + message + ANSI_RESET);
  }

  public static void info(String message) {
    io(ANSI_BLUEY, "INFO", message);
  }

  public static void fail(String message) {
    io(ANSI_REDDY, "FAIL", message);
  }

  public static void warn(String message) {
    io(ANSI_YELLY, "WARN", message);
  }

  public static void done(String message) {
    io(ANSI_GREEN, "DONE", message);
  }

  public static void look(String message) {
    io(ANSI_PURPY, "LOOK", message);
  }

  public static void cyan(String message) {
    io(ANSI_CYANY, "CYAN", message);
  }

  public static void white(String message) {
    io(ANSI_WHITE, "WHITE", message);
  }

  public static void time(String message) {
    io(ANSI_BLACK, "TIME", message);
  }

  // STOP, SKIP, SAVE, COPY

}
