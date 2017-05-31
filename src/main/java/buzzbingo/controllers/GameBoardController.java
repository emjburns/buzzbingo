package buzzbingo.controllers;

import buzzbingo.BuzzUtils;
import buzzbingo.exceptions.GameBoardNotFoundException;
import buzzbingo.exceptions.GameNotInPlayException;
import buzzbingo.exceptions.InvalidMoveException;
import buzzbingo.model.Game;
import buzzbingo.model.GameBoard;
import buzzbingo.model.TurnResult;
import buzzbingo.repositories.GameBoardRepository;
import buzzbingo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ApiBaseController.API_VERSION + "/gameboard")
public class GameBoardController extends ApiBaseController{
  @Autowired
  protected GameBoardRepository gameBoardRepository;

  @Autowired
  protected GameRepository gameRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Map<Object, Object> getGameBoards() {
    return gameBoardRepository.findAllGameBoards();
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public GameBoard getGameBoard(@PathVariable String name) throws GameBoardNotFoundException {
    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
    if (gameBoard == null) throw new GameBoardNotFoundException();
    return gameBoard;
  }


  @RequestMapping(value = "/{name}/{index}", method = RequestMethod.PUT)
  public GameBoard toggleSquare(@PathVariable String name, @PathVariable Integer index) throws GameBoardNotFoundException, GameNotInPlayException, InvalidMoveException {
    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
    if (gameBoard == null) throw new GameBoardNotFoundException();
    Game game = gameRepository.findGame(gameBoard.getGameName());
    if (game.hasWinner()) throw new GameNotInPlayException();

    TurnResult turnResult = gameBoard.toggleSquare(index);
    if (turnResult == TurnResult.INVALID){
      throw new InvalidMoveException();
    }
    System.out.println("Player [" +gameBoard.getPlayerName() + "] clicked spot [" + index + "]");

    gameBoardRepository.saveGameBoard(gameBoard);
    return gameBoard;
  }

  @RequestMapping(value = "/{name}/bingo", method = RequestMethod.PUT)
  public GameBoard sayBingo(@PathVariable String name) throws GameBoardNotFoundException, GameNotInPlayException {
    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
    if (gameBoard == null) throw new GameBoardNotFoundException();

    Game game = gameRepository.findGame(gameBoard.getGameName());
    if (game.hasWinner()) throw new GameNotInPlayException();

    System.out.println("Player [" +gameBoard.getPlayerName() + "] thinks they have bingo");

    Boolean reallyBingo = gameBoard.declareBingo();
    gameBoardRepository.saveGameBoard(gameBoard);

    if (reallyBingo) {
      System.out.println("Indeed, Player [" +gameBoard.getPlayerName() + "] has BINGO!");
      game.setWinner(true);
      game.setWinnerName(gameBoard.getPlayerName());
      gameRepository.saveGame(game);

      updatePlayersGameStatus(game);
    }

    return gameBoard;
  }

  private void updatePlayersGameStatus(Game game){
    // Each board needs to reflect game status
    for(String player : game.getPlayers()){
      GameBoard gameBoard = gameBoardRepository.findGameBoard(BuzzUtils.gameboardname(game.getName(),player));
      gameBoard.setGameover(true);
      gameBoardRepository.saveGameBoard(gameBoard);
    }
  }
}
