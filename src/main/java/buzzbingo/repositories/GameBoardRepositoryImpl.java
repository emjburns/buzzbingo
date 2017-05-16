package buzzbingo.repositories;

import buzzbingo.model.GameBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class GameBoardRepositoryImpl implements GameBoardRepository {
  private static final String KEY = "GAMEBOARD";

  private RedisTemplate<String, GameBoard> redisTemplate;
  private HashOperations hashOps;

  @Autowired
  public GameBoardRepositoryImpl(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  void init() {
    hashOps = redisTemplate.opsForHash();
  }

  @Override
  public void saveGameBoard(GameBoard gameBoard) {
    hashOps.put(KEY, gameBoard.getPlayerName(), gameBoard);
  }

  @Override
  public void updateGameBoard(GameBoard gameBoard) {
    hashOps.put(KEY, gameBoard.getPlayerName(), gameBoard);
  }

  @Override
  public GameBoard findGameBoard(String playerName) {
    return (GameBoard) hashOps.get(KEY, playerName);
  }

  @Override
  public Map<Object, Object> findAllGameBoards() {
    return hashOps.entries(KEY);
  }

  @Override
  public void deleteGameBoard(String name) {
    hashOps.delete(KEY, name);
  }
}
