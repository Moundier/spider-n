package com.example.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimeFormatted {
  
  public static String now() {
    return new String(
      DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(ZonedDateTime.of(
        LocalDate.now(), 
        LocalTime.now(), 
        ZoneId.of("America/Sao_Paulo"))));
  }
}
