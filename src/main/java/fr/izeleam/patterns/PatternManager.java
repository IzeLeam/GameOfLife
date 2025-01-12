package fr.izeleam.patterns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.izeleam.Main;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages patterns in the game of life.
 */
public class PatternManager {

  private static final PatternManager instance = new PatternManager();
  private final List<Pattern> patterns = new ArrayList<>();

  /**
   * Constructor.
   */
  private PatternManager() {
  }

  /**
   * Get the instance of the pattern manager.
   *
   * @return The instance of the pattern manager.
   */
  public static PatternManager getInstance() {
    return instance;
  }

  /**
   * Get the patterns.
   *
   * @return The patterns.
   */
  public List<Pattern> getPatterns() {
    return patterns;
  }

  /**
   * Get a pattern by name.
   *
   * @param name The name of the pattern.
   * @return The pattern.
   */
  public Pattern getByName(String name) {
    return patterns.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
  }

  /**
   * Load the patterns from the JSON file.
   */
  public void loadPatterns() {
    try (InputStream inputStream = Main.class.getResourceAsStream("patterns.json")) {
      if (inputStream == null) {
        throw new FileNotFoundException("Patterns file not found.");
      }

      JsonNode root = new ObjectMapper().readTree(inputStream);
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