package buzzbingo.controllers;

import buzzbingo.exceptions.GameBoardNotFoundException;
import buzzbingo.exceptions.GameNotInPlayException;
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
  public GameBoard toggleSquare(@PathVariable String name, @PathVariable Integer index) throws GameBoardNotFoundException, GameNotInPlayException {
    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
    if (gameBoard == null) throw new GameBoardNotFoundException();
    Game game = gameRepository.findGame(gameBoard.getGameName());
    if (game.hasWinner() == false) throw new GameNotInPlayException();

    TurnResult turnResult = gameBoard.toggleSquare(index);
    if (turnResult == TurnResult.GAMEOVER) {
      game.setWinner(true);
      gameRepository.saveGame(game);
      //TODO: make sure game is ended automatically
    }
    gameBoardRepository.saveGameBoard(gameBoard);
    return gameBoard;
  }



//  @RequestMapping(value = "/{name}/{index}", method = RequestMethod.PUT)
//  public GameBoard makeMove(@PathVariable String name, @PathVariable Integer index) throws GameBoardNotFoundException, GameNotInPlayException {
//    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
//    if (gameBoard == null) throw new GameBoardNotFoundException();
//    Game game = gameRepository.findGame(gameBoard.getGameName());
//    if (game.hasWinner() == false) throw new GameNotInPlayException();
//
//    TurnResult turnResult = gameBoard.toggleSquare(index);
//    if (turnResult == TurnResult.GAMEOVER) {
//      game.setWinner(true);
//      gameRepository.saveGame(game);
//      //TODO: make sure game is ended automatically
//    }
//    gameBoardRepository.saveGameBoard(gameBoard);
//    return gameBoard;
//
//  }
//
//  @RequestMapping(value = "/{name}/{index}", method = RequestMethod.DELETE)
//  public GameBoard retractMove(@PathVariable String name, @PathVariable Integer index) throws GameBoardNotFoundException, GameNotInPlayException {
//    GameBoard gameBoard = gameBoardRepository.findGameBoard(name);
//    if (gameBoard == null) throw new GameBoardNotFoundException();
//    Game game = gameRepository.findGame(gameBoard.getGameName());
//    if (game.hasWinner() == false) throw new GameNotInPlayException();
//
//    TurnResult turnResult = gameBoard.toggleSquare(index);
//    gameBoardRepository.saveGameBoard(gameBoard);
//    return gameBoard;
//  }

}
