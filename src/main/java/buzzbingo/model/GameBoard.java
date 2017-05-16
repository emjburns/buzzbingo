package buzzbingo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class GameBoard implements Serializable {
  String playerName;
  String gameName;
  Integer dimension = 5;
  Integer gameSize = dimension*dimension;
  List<String> words = new ArrayList<>();
  List<GameSquare> board = new ArrayList<>();
  Integer numHits = 0;

  public GameBoard() {
  }

  public GameBoard(String playerName, String gameName, Set<String> words) {
    //need to pass in the words of the game
    System.out.println("creating game board");
    this.playerName = playerName;
    this.gameName = gameName;
    this.dimension = dimension;

    setWordlist(words);
    buildBoard();
    System.out.println("game board");
  }

  public GameBoard buildBoard() {
    System.out.println("building board");
    for (String w : this.words) {
      board.add(new GameSquare(w));
    }
    return this;
  }

  public TurnResult unmarkSquare(Integer index) {
    if (index >= gameSize || index < 0) return TurnResult.INVALID;
    GameSquare gameSquare = board.get(index);
    if (gameSquare.getMarked()) {
      gameSquare.setMarked(false);
      numHits--;
    }
    return TurnResult.GAMENOTOVER;
  }

  public TurnResult markSquare(Integer index) {
    if (index >= gameSize || index < 0) return TurnResult.INVALID;
    GameSquare gameSquare = board.get(index);
    if (!gameSquare.getMarked()) {
      gameSquare.setMarked(true);
      numHits++;
      if (hasBingo()) return TurnResult.GAMEOVER;
    }
    return TurnResult.GAMENOTOVER;
  }

  private void setWordlist(Set<String> words) {
    List<String> listwords = new ArrayList<>(words);
    while (listwords.size() < 9){
      // If there are less than 9 words, double until there are more.
      listwords.addAll(listwords);
    }
    Collections.shuffle(listwords);
    if (listwords.size() < dimension){
      Integer newDimension = ((Double) Math.floor(Math.sqrt(listwords.size()))).intValue();
    }
    if (listwords.size() > gameSize) {
      listwords = listwords.subList(0, gameSize);
    }
    this.words = listwords;
    System.out.println("words: " + this.words);
  }

  public Boolean hasBingo() {
    //returns true if board is bingo, false otherwise
    if (numHits < 5) return false;
    if (numHits > 19) return true;
    //TODO: algorithm for determining winner
    return false;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public Integer getDimension() {
    return dimension;
  }

  public void setDimension(Integer dimension) {
    this.dimension = dimension;
  }

  public Integer getGameSize() {
    return gameSize;
  }

  public void setGameSize(Integer gameSize) {
    this.gameSize = gameSize;
  }

  public List<String> getWords() {
    return words;
  }

  public void setWords(List<String> words) {
    this.words = words;
  }

  public List<GameSquare> getBoard() {
    return board;
  }

  public void setBoard(List<GameSquare> board) {
    this.board = board;
  }

  public Integer getNumHits() {
    return numHits;
  }

  public void setNumHits(Integer numHits) {
    this.numHits = numHits;
  }

  @Override
  public String toString() {
    return "GameBoard{" +
        "playerName='" + playerName + '\'' +
        ", gameName='" + gameName + '\'' +
        ", dimension=" + dimension +
        ", gameSize=" + gameSize +
        ", words=" + words +
        ", board=" + board +
        ", numHits=" + numHits +
        '}';
  }
}
