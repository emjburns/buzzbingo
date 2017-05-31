package buzzbingo.model;

import buzzbingo.BuzzUtils;
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
  List<GameSquare> board = new ArrayList<>();
  Integer numHits = 1; // free space countss

  Boolean bingo = false;
  Boolean gameover = false;

  private Integer dimension = 5;
  private Integer gameSize = dimension*dimension;

  public GameBoard() {
  }

  public GameBoard(String playerName, String gameName, Set<String> words) {
    //TODO: get this from game object?
    // feels clunky that you need to pass in the words of the game
    System.out.println("creating game board for " + playerName);
    this.playerName = playerName;
    this.gameName = gameName;
    gameBoardName = BuzzUtils.gameboardname(gameName, playerName);

    buildBoard(words);
  }

  private GameBoard buildBoard(Set<String> words) {
    List<String> wordlist = setWordlist(words);
    System.out.println("building board");
    for( int i = 0; i< gameSize; i++){
      if (i == gameSize/2) {
//        add middle free space
        board.add(new GameSquare("FREE", i,true));
      } else {
        board.add(new GameSquare(wordlist.get(i),i));
      }
    }
    return this;
  }

  private List<String> setWordlist(Set<String> words) {
    List<String> listwords = new ArrayList<>();
    listwords.addAll(words);
    while (listwords.size() < 24){
      // If there are less than 24 words, double until there are more.
      listwords.addAll(listwords);
    }
    Collections.shuffle(listwords);
    if (listwords.size() > gameSize) {
      ArrayList<String> newWords = new ArrayList<>();
      newWords.addAll(listwords.subList(0, gameSize));
      listwords = newWords;
    }
    return listwords;
  }

  public TurnResult toggleSquare(Integer index) {
    if (index >= gameSize || index < 0) return TurnResult.INVALID;
    GameSquare gameSquare = board.get(index);

    if (gameSquare.isMarked()) {
      gameSquare.setMarked(false);
      numHits--;
    } else {
      // not marked
      gameSquare.setMarked(true);
      numHits++;
    }
    return TurnResult.VALID;
  }

  public Boolean declareBingo() {
    System.out.println("..so, does [" + playerName + "] have bingo?");
    //TODO: should they be kicked out of the game if they declare wrongly?
    bingo = hasBingo();
    System.out.println("bingo variable: " + bingo);
    if (bingo) {
      gameover = true;
    }
    return bingo;
  }

  private Boolean hasBingo() {
    //returns true if board is bingo, false otherwise
    if (numHits < 5) return false;
    if (numHits > 19) return true;
    //check all horiz
    // 0 1 2 3 4
    // 5 6 7 8 9
    // 10 11 12 13 14
    // 15 16 17 18 19
    // 20 21 22 23 24

    for (int i=0; i<25; i=i+5) {
      if (board.get(i).isMarked() &&
          board.get(i+1).isMarked() &&
          board.get(i+2).isMarked() &&
          board.get(i+3).isMarked() &&
          board.get(i+4).isMarked()) {
        System.out.println("Bingo in gameboard [" + gameBoardName+ "] in row starting from " + i);
        return true;
      }
    }

    //check all vert
    // 0 5 10 15 20
    // 1 6 11 16 21
    // 2 7 12 17 22
    // 3 8 13 18 23
    // 4 9 14 19 24
    for (int i=0; i<5; i++) {
      if (board.get(i).isMarked() &&
          board.get(i+5).isMarked() &&
          board.get(i+10).isMarked() &&
          board.get(i+15).isMarked() &&
          board.get(i+20).isMarked()) {
        System.out.println("Bingo in gameboard [" + gameBoardName+ "] in col starting from " + i);
        return true;
      }
    }

    //check criss cross
    // 0 6 12 18 24
    if (board.get(0).isMarked() &&
        board.get(6).isMarked() &&
        board.get(12).isMarked() &&
        board.get(18).isMarked() &&
        board.get(24).isMarked()) {
      System.out.println("Bingo in gameboard [" + gameBoardName+ "] in slash \\");
      return true;
    }

    // 4 8 12 16 20
    if (board.get(4).isMarked() &&
        board.get(8).isMarked() &&
        board.get(12).isMarked() &&
        board.get(16).isMarked() &&
        board.get(20).isMarked()) {
      System.out.println("Bingo in gameboard [" + gameBoardName+ "] in slash /");
      return true;
    }


    System.out.println("Alas, player [" + playerName + "] does not have bingo. LIAR.");
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

  public Boolean getBingo() {
    return bingo;
  }

  public void setBingo(Boolean bingo) {
    this.bingo = bingo;
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

  public Boolean getGameover() {
    return gameover;
  }

  public void setGameover(Boolean gameover) {
    this.gameover = gameover;
  }

  @Override
  public String toString() {
    return "GameBoard{" +
        "playerName='" + playerName + '\'' +
        ", gameName='" + gameName + '\'' +
        ", gameBoardName='" + gameBoardName + '\'' +
        ", board=" + board +
        ", numHits=" + numHits +
        ", bingo=" + bingo +
        '}';
  }
}
