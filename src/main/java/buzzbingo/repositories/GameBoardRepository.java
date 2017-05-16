package buzzbingo.repositories;

import buzzbingo.model.GameBoard;

import java.util.Map;

public interface GameBoardRepository {
  void saveGameBoard(GameBoard gameBoard);
  void updateGameBoard(GameBoard gameBoard);
  GameBoard findGameBoard(String name);
  Map<Object, Object> findAllGameBoards();
  void deleteGameBoard(String name);
}
