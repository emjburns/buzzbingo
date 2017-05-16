package buzzbingo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
public class Wordbank implements Serializable{

  String name;
  Set<String> words = new HashSet<>();

  public Wordbank() {
  }

  public Wordbank(String name, Set<String> words) {
    this.name = name;
    this.words = words;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getWords() {
    return new HashSet<String>(words);
  }

  public void setWords(Set<String> words) {
    this.words = words;
  }

  @Override
  public String toString() {
    return "Wordbank{" +
        "name='" + name + '\'' +
        ", words=" + words +
        '}';
  }
}
