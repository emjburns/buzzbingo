package buzzbingo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GameSquare implements Serializable {

  String value;
  Boolean marked = false;
  Integer index = -1;

  public GameSquare() {
  }

  public String getValue() {
    return value;
  }

  public GameSquare(String value, Integer index) {
    this(value, index, false);
  }

  public GameSquare(String value, Integer index, Boolean marked) {
    this.value = value;
    this.index = index;
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

  public Boolean getMarked() {
    return marked;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  @Override
  public String toString() {
    return "GameSquare{" +
        "value='" + value + '\'' +
        ", marked=" + marked +
        ", index=" + index +
        '}';
  }
}
