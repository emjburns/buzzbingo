package buzzbingo.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class GameRepositoryImpl implements GameRepository {

  private static final String KEY = "GAME";

  private RedisTemplate<String, Game> redisTemplate;
  private HashOperations hashOps;

  @Autowired
  public GameRepositoryImpl(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  void init() {
    hashOps = redisTemplate.opsForHash();
  }

  @Override
  public void saveGame(Game game) {
    hashOps.put(KEY,game.getName(), game);
  }

  @Override
  public void updateGame(Game game) {
    hashOps.put(KEY,game.getName(), game);
  }

  @Override
  public Game findGame(String name) {
    return (Game) hashOps.get(KEY, name);
  }

  @Override
  public Map<Object, Object> findAllGames() {
    return hashOps.entries(KEY);
  }

  @Override
  public void deleteGame(String name) {
    hashOps.delete(KEY, name);
  }
}
