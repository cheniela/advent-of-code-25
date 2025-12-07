package com.cherniela.utils;

import java.io.IOException;
import java.util.List;

public class AdventOfCode {
  public static List<String> getAllLines(String inputFileName) throws IOException {
    var basePath = getBaseFilePath();
    return ResourceUtils.readAllLinesFromResourceFile("/" + basePath + "/" + inputFileName);
  }

  private static String getBaseFilePath() {
    try {
      var properties = ResourceUtils.readAllLinesFromResourceFile("/.properties");
      return properties.stream()
                       .filter(x -> x.startsWith("resource_base_path="))
                       .findFirst()
                       .map(x -> x.split("=")[1])
                       .orElseThrow(() -> new IllegalStateException("Property is missing"));
    }
    catch (IOException e) {
      System.out.println("Could not read properties file. Exiting");
      System.exit(1);
      return null;
    }
  }
}
