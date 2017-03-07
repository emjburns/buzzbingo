package buzzbingo.game;

import buzzbingo.ApiBaseController;
import buzzbingo.exceptions.DuplicateGameException;
import buzzbingo.exceptions.GameNotFoundException;
import buzzbingo.exceptions.WordbankNotFoundException;
import buzzbingo.wordbank.Wordbank;
import buzzbingo.wordbank.WordbankRepository;
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

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Map<Object, Object> getGames() {
    return gameRepository.findAllGames();
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public Game getGame(@PathVariable String name) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    return game;
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.POST)
  public Game createGame(@PathVariable String name, @RequestBody String wordbank) throws WordbankNotFoundException, DuplicateGameException {
    if (wordbankRepository.findWordbank(wordbank) == null) {
      throw new WordbankNotFoundException();
    }
    if (gameRepository.findGame(name) != null) {
      throw new DuplicateGameException();
    }

    Game game = new Game();
    game.setName(name).setWordbank(wordbank);
    gameRepository.saveGame(game);

    return game;
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
  public Game updateGame(@PathVariable String name, @RequestBody Game game) throws GameNotFoundException, WordbankNotFoundException {
    // Update entire object to new object
    if (!gameExists(name)) throw new GameNotFoundException();
    if (!wordbankExists(game.getWordbank())) throw new WordbankNotFoundException();
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/wordbank", method = RequestMethod.PUT)
  public Game updateGameWordbank(@PathVariable String name, @RequestBody String wordbank) throws GameNotFoundException, WordbankNotFoundException {
    // Update entire object to new object
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    if (!wordbankExists(wordbank)) throw new WordbankNotFoundException();
    game.setWordbank(wordbank);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/size", method = RequestMethod.PUT)
  public Game updateGameSize(@PathVariable String name, @RequestBody Integer dimension) throws GameNotFoundException {
    // Update entire object to new object
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    game.setDimention(dimension);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/players", method = RequestMethod.GET)
  public SortedSet<String> getPlayers(@PathVariable String name, @RequestBody List<String> players) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    return game.getPlayers();
  }

  @RequestMapping(value = "/{name}/players", method = RequestMethod.PUT)
  public Game addPlayers(@PathVariable String name, @RequestBody List<String> players) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    game.addPlayers(players);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/players", method = RequestMethod.DELETE)
  public Game removePlayers(@PathVariable String name, @RequestBody List<String> players) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    game.removePlayers(players);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/play", method = RequestMethod.PUT)
  public Game startGame(@PathVariable String name) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    game.setInPlay(true);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}/play", method = RequestMethod.DELETE)
  public Game stopGame(@PathVariable String name) throws GameNotFoundException {
    Game game = gameRepository.findGame(name);
    if (game == null) throw new GameNotFoundException();
    game.setInPlay(false);
    gameRepository.saveGame(game);
    return game;
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
  public void deleteGame(@PathVariable String name) throws GameNotFoundException {
    if (!gameExists(name)) throw new GameNotFoundException();
    gameRepository.deleteGame(name);
  }

  protected Boolean wordbankExists(String name) {
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    return wordbank != null;
  }

  protected Boolean gameExists(String name) {
    Game game = gameRepository.findGame(name);
    return game != null;
  }

}
