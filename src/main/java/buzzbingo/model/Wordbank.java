package buzzbingo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class Wordbank implements Serializable{

  String name;
  Set<String> words = new HashSet<>();

  @JsonSerialize(using=UUIDSerializer.class)
  @JsonDeserialize(using=UUIDDeserializer.class)
  UUID id;

  public Wordbank() {
  }

  public Wordbank(String name, Set<String> words) {
    this.name = name;
    this.words = words;
    id = UUID.randomUUID();
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

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Wordbank{" +
        "name='" + name + '\'' +
        ", words=" + words +
        ", id=" + id +
        '}';
  }
}
