package com.multithreading.util;

import static java.text.MessageFormat.format;

import java.io.*;

public class PrinterUtil {

  final OutputStream outputStream;
  final PrintWriter writer;

  public PrinterUtil() {
    this.outputStream = new BufferedOutputStream(System.out);
    this.writer = new PrintWriter(outputStream);
  }

  public void print(String pattern, Object... objects) {
    writer.println(format(pattern, objects));
    writer.flush();
  }
}
