package buzzbingo.wordbank;

import java.util.Map;

public interface WordbankRepository {
  void saveWordbank(Wordbank wordbank);
  void updateWordbank(Wordbank wordbank);
  Wordbank findWordbank(String name);
  Map<Object, Object> findAllWordbanks();
  void deleteWordbank(String name);
}
