package com.multithreading.util;

public class ColorUtil {
  public static String colorize(String message, String colorCode) {
    return colorCode + message + "\033[0m"; // Reset color after message
  }
}
