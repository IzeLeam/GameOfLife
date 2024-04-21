package fr.izeleam.patterns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.izeleam.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PatternManager {

  private static final PatternManager instance = new PatternManager();
  private final List<Pattern> patterns = new ArrayList<>();

  private PatternManager() {
  }

  public static PatternManager getInstance() {
    return instance;
  }

  public List<Pattern> getPatterns() {
    return patterns;
  }

  public Pattern getByName(String name) {
    return patterns.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
  }

  public void loadPatterns() {
    File file = new File(Main.class.getResource("patterns.json").getFile());

    try {
      JsonNode root = new ObjectMapper().readTree(file);
      root.fields().forEachRemaining(entry -> {
        JsonNode pattern = entry.getValue();
        String name = pattern.get("name").asText();
        String size = pattern.get("size").asText();
        String serialised = pattern.get("pattern").asText();
        patterns.add(new Pattern(name, size, serialised));
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}