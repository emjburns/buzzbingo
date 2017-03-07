package buzzbingo.wordbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

//http://www.baeldung.com/spring-data-redis-tutorial
@Repository
public class WordbankRepositoryImpl implements WordbankRepository {
  private static final String KEY = "WORDBANK";

  private RedisTemplate<String, Wordbank> redisTemplate;
  private HashOperations hashOps;
  private SetOperations setOps;

  @Autowired
  public WordbankRepositoryImpl(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  void init() {
    hashOps = redisTemplate.opsForHash();
    setOps = redisTemplate.opsForSet();
  }

  public void saveWordbank(Wordbank wordbank) {
    hashOps.put(KEY, wordbank.getName(), wordbank);
  }

  public void updateWordbank(Wordbank wordbank) {
    hashOps.put(KEY, wordbank.getName(), wordbank);
  }

  public Wordbank findWordbank(String name) {
    return (Wordbank) hashOps.get(KEY, name);
  }

  public Map<Object, Object> findAllWordbanks() {
    return hashOps.entries(KEY);
  }

  public void deleteWordbank(String name) {
    hashOps.delete(KEY, name);
  }
}
