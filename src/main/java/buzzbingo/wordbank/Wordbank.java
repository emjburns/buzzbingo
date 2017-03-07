package buzzbingo.wordbank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Wordbank implements Serializable{

  String name;
  Set<String> words = new HashSet<>();

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
