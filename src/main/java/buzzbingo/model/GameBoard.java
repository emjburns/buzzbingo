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
  String gameBoardName;
  List<String> words = new ArrayList<>();
  List<GameSquare> board = new ArrayList<>();
  Integer numHits = 0;

  private Integer dimension = 5;
  private Integer gameSize = dimension*dimension;

  public GameBoard() {
  }

  public GameBoard(String playerName, String gameName, Set<String> words) {
    //need to pass in the words of the game
    System.out.println("creating game board for " + playerName);
    this.playerName = playerName;
    this.gameName = gameName;
    gameBoardName = this.gameName + this.playerName;

    setWordlist(words);
    buildBoard();
  }

  public GameBoard buildBoard() {
    System.out.println("building board");
    for( int i = 0; i< gameSize; i++){
      if (i == gameSize/2) {
        //add middle free space
        board.add(new GameSquare("FREE", true));
      } else {
        board.add(new GameSquare(words.get(i)));
      }
    }
    return this;
  }

  public TurnResult toggleSquare(Integer index) {
    if (index >= gameSize || index < 0) return TurnResult.INVALID;
    GameSquare gameSquare = board.get(index);

    if (gameSquare.isMarked()) {
      gameSquare.setMarked(false);
      numHits--;
    } else if (!gameSquare.isMarked()) {
      gameSquare.setMarked(true);
      numHits++;
      if (hasBingo()) return TurnResult.GAMEOVER;
    }
    return TurnResult.GAMENOTOVER;
  }

  private void setWordlist(Set<String> words) {
    List<String> listwords = new ArrayList<>(words);
    while (listwords.size() < 24){
      // If there are less than 24 words, double until there are more.
      listwords.addAll(listwords);
    }
    Collections.shuffle(listwords);
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
    //check all horiz
    //check all vert
    //check criss cross
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

  public String getGameBoardName() {
    return gameBoardName;
  }

  public void setGameBoardName(String gameBoardName) {
    this.gameBoardName = gameBoardName;
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
        ", gameBoardName='" + gameBoardName + '\'' +
        ", words=" + words +
        ", board=" + board +
        ", numHits=" + numHits +
        '}';
  }
}
