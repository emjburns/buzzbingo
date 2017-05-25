package buzzbingo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GameSquare implements Serializable {

  String value;
  Boolean marked = false;

  public GameSquare() {
  }

  public String getValue() {
    return value;
  }

  public GameSquare(String value) {
    this.value = value;
  }

  public GameSquare(String value, Boolean marked) {
    this.value = value;
    this.marked = marked;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Boolean isMarked() {
    return marked;
  }

  public void setMarked(Boolean marked) {
    this.marked = marked;
  }

  @Override
  public String toString() {
    return "GameSquare{" +
        "value='" + value + '\'' +
        ", marked=" + marked +
        '}';
  }
}
