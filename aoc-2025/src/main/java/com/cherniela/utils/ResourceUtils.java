package com.cherniela.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResourceUtils {
  public static List<String> readAllLinesFromResourceFile(String resourcePath) throws IOException {
    try (InputStream resource = ResourceUtils.class.getResourceAsStream(resourcePath)) {
      if (resource == null) {
        throw new FileNotFoundException(resourcePath);
      }
      try (InputStreamReader inputStreamReader = new InputStreamReader(resource, StandardCharsets.UTF_8);
           BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
        return bufferedReader.lines().toList();
      }
    }
  }
}
