package buzzbingo.game;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Game implements Serializable {
  String name;
  String wordbank;
  SortedSet<String> players = new TreeSet<>();
  Boolean inPlay = false;
  Boolean winner = false;
  Integer dimention = 5;


  public Game(){
  }

  public Game(String name, String wordbank, SortedSet<String> players, Boolean inPlay, Boolean winner, Integer dimention) {
    this.name = name;
    this.wordbank = wordbank;
    this.players = players;
    this.inPlay = inPlay;
    this.winner = winner;
    this.dimention = dimention;
  }

  public String getName() {
    return name;
  }

  public Game setName(String name) {
    this.name = name;
    return this;
  }

  public String getWordbank() {
    return wordbank;
  }

  public Game setWordbank(String wordbank) {
    this.wordbank = wordbank;
    return this;
  }

  public SortedSet<String> getPlayers() {
    return players;
  }

  public Game setPlayers(SortedSet<String> players) {
    this.players = players;
    return this;
  }

  public Game addPlayers(List<String> newPlayers) {
    players.addAll(newPlayers);
    return this;
  }

  public Game removePlayers(List<String> oldPlayers) {
    players.removeAll(oldPlayers);
    return this;
  }

  public Boolean getInPlay() {
    return inPlay;
  }

  public Game setInPlay(Boolean inPlay) {
    this.inPlay = inPlay;
    return this;
  }

  public Boolean getWinner() {
    return winner;
  }

  public Game setWinner(Boolean winner) {
    this.winner = winner;
    return this;
  }

  public Integer getDimention() {
    return dimention;
  }

  public Game setDimention(Integer dimention) {
    this.dimention = dimention;
    return this;
  }
}
