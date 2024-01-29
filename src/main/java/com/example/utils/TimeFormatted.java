package com.example.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimeFormatted {
    

  private static LocalDate anotherSummerDay = LocalDate.now();
  private static LocalTime anotherTime = LocalTime.now();
  private static ZonedDateTime zonedDateTime = ZonedDateTime.of(anotherSummerDay, anotherTime,
      ZoneId.of("America/Sao_Paulo"));
  private static String formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime);

  public static String now() {
    return formatter;
  }
}
