package buzzbingo.game;

import java.util.Map;

public interface GameRepository {
  void saveGame(Game game);
  void updateGame(Game game);
  Game findGame(String name);
  Map<Object, Object> findAllGames();
  void deleteGame(String name);
}
