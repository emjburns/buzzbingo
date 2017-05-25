package buzzbingo.controllers;

import buzzbingo.exceptions.DuplicateGameException;
import buzzbingo.exceptions.DuplicatePlayerException;
import buzzbingo.exceptions.GameNotFoundException;
import buzzbingo.exceptions.WordbankNotFoundException;
import buzzbingo.model.Game;
import buzzbingo.model.GameBoard;
import buzzbingo.model.Wordbank;
import buzzbingo.repositories.GameBoardRepository;
import buzzbingo.repositories.GameRepository;
import buzzbingo.repositories.WordbankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@RestController
@RequestMapping(ApiBaseController.API_VERSION + "/game")
public class GameController extends ApiBaseController{

  @Autowired
  protected GameRepository gameRepository;

  @Autowired
  protected WordbankRepository wordbankRepository;

  @Autowired
  protected GameBoardRepository gameBoardRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Map<Object, Object> getGames() {
    return gameRepository.findAllGames();
  }

  @RequestMapping(value = "/{gameName}", method = RequestMethod.GET)
  public Game getGame(@PathVariable String gameName) throws GameNotFoundException {
    Game game = gameRepository.findGame(gameName);
    if (game == null) throw new GameNotFoundException();
    return game;
  }

  //TODO: this should support a body of {wordbank:"mywordbank"} not just a string
  @RequestMapping(value = "/{gameName}", method = RequestMethod.POST)
  public Game createGame(@PathVariable String gameName, @RequestBody String wordbank) throws WordbankNotFoundException, DuplicateGameException {
    System.out.println("GAME: " + gameName + ", WORDBANK: " + wordbank);

    if (wordbankRepository.findWordbank(wordbank) == null) {
      throw new WordbankNotFoundException();
    }
    if (gameRepository.findGame(gameName) != null) {
      throw new DuplicateGameException();
    }

    Game game = new Game();
    game.setName(gameName).setWordbank(wordbank);
    gameRepository.saveGame(game);

    return game;
  }

  @RequestMapping(value = "/{gameName}", method = RequestMethod.PUT)
  public Game updateGame(@PathVariable String gameName, @RequestBody Game game) throws GameNotFoundException, WordbankNotFoundException {
    // Update entire object to new object
    if (!gameExists(gameName)) throw new GameNotFoundException();
    if (!wordbankExists(game.getWordbank())) throw new WordbankNotFoundException();
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{gameName}/wordbank", method = RequestMethod.PUT)
  public Game updateGameWordbank(@PathVariable String gameName, @RequestBody String wordbank) throws GameNotFoundException, WordbankNotFoundException {
    // Update entire object to new object
    Game game = gameRepository.findGame(gameName);
    if (game == null) throw new GameNotFoundException();
    if (!wordbankExists(wordbank)) throw new WordbankNotFoundException();
    game.setWordbank(wordbank);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{gameName}", method = RequestMethod.DELETE)
  public void deleteGame(@PathVariable String gameName) throws GameNotFoundException {
    if (!gameExists(gameName)) throw new GameNotFoundException();
    gameRepository.deleteGame(gameName);
  }

  @RequestMapping(value = "/{gameName}/players", method = RequestMethod.GET)
  public SortedSet<String> getPlayers(@PathVariable String gameName, @RequestBody List<String> players) throws GameNotFoundException {
    Game game = gameRepository.findGame(gameName);
    if (game == null) throw new GameNotFoundException();
    return game.getPlayers();
  }

  @RequestMapping(value = "/{gameName}/players", method = RequestMethod.PUT)
  public Game addPlayers(@PathVariable String gameName, @RequestBody List<String> players) throws GameNotFoundException, DuplicatePlayerException {
    Game game = gameRepository.findGame(gameName);
    if (game == null) throw new GameNotFoundException();
    game.addPlayers(players);
    Wordbank wordbank = wordbankRepository.findWordbank(game.getWordbank());

    for (String player : players) {
      System.out.println("Player: " +player);
      GameBoard gameBoard = gameBoardRepository.findGameBoard(player);
      System.out.println("found: " + gameBoard);

      if (gameBoard != null) {
        if (gameBoard.getGameName() == gameName) throw new DuplicatePlayerException(); //this is a duplicate
        //otherwise, just a leftover board, clean it up
        gameBoardRepository.deleteGameBoard(player);
      }

      gameBoard = new GameBoard(player, gameName, wordbank.getWords());
      System.out.println(gameBoard);
      gameBoardRepository.saveGameBoard(gameBoard);
    }
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{gameName}/players", method = RequestMethod.DELETE)
  public Game removePlayers(@PathVariable String gameName, @RequestBody List<String> players) throws GameNotFoundException {
    Game game = gameRepository.findGame(gameName);
    if (game == null) throw new GameNotFoundException();
    game.removePlayers(players);
    for (String player : players) {
      //remove board for player that are removed from a game.
      gameBoardRepository.deleteGameBoard(player);
    }
    gameRepository.saveGame(game);
    return game;
  }

  protected Boolean wordbankExists(String gameName) {
    Wordbank wordbank = wordbankRepository.findWordbank(gameName);
    return wordbank != null;
  }

  protected Boolean gameExists(String gameName) {
    Game game = gameRepository.findGame(gameName);
    return game != null;
  }

}
