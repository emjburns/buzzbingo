package buzzbingo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class Game implements Serializable {
  String name;
  String wordbank;
  SortedSet<String> players = new TreeSet<>();
  Boolean winner = false;
  String winnerName;

  public Game(){
  }

  public Game(String name, String wordbank, SortedSet<String> players) {
    this.name = name;
    this.wordbank = wordbank;
    this.players = players;
  }

  public Game(String name, String wordbank, SortedSet<String> players, Boolean winner) {
    this.name = name;
    this.wordbank = wordbank;
    this.players = players;
    this.winner = winner;
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

  public Boolean getWinner() {
    return winner;
  }

  public Boolean hasWinner() {
    return winner;
  }

  public Game setWinner(Boolean winner) {
    this.winner = winner;
    return this;
  }

  @Override
  public String toString() {
    return "Game{" +
        "name='" + name + '\'' +
        ", wordbank='" + wordbank + '\'' +
        ", players=" + players +
        ", winner=" + winner +
        ", winnerName='" + winnerName + '\'' +
        '}';
  }

  public String getWinnerName() {
    return winnerName;
  }

  public void setWinnerName(String winnerName) {
    this.winnerName = winnerName;
  }

}
