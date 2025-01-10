package src.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
  public static void log(String message) {
    // generate current time and format it
    LocalDateTime now = LocalDateTime.now();
    String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // print log
    System.out.println("[" + time + "] " + message);
  }
}
