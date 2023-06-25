package com.furkanuluay.recipeservice.xmlloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Furkan Uluay
 */

@Component
public class XmlFileReader {

  public List<String> readXmlFiles(List<Resource> resources) {
    return resources.stream().map(this::readXmlFromFileUnchecked).collect(Collectors.toList());
  }

  private String readXmlFromFileUnchecked(Resource resource) {
    try {
      StringBuilder textBuilder = new StringBuilder();
      try (BufferedReader bufferedReader =
          new BufferedReader(
              new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          textBuilder.append(line);
        }
      }
      return textBuilder.toString();
    } catch (IOException e) {
      throw new RuntimeException("Error reading XML file: " + e.getMessage(), e);
    }
  }
}
