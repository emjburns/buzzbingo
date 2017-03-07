package buzzbingo.wordbank;

import buzzbingo.ApiBaseController;
import buzzbingo.exceptions.DuplicateWordbankException;
import buzzbingo.exceptions.WordbankNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(ApiBaseController.API_VERSION + "/wordbank")
public class WordbankController extends ApiBaseController {

  @Autowired
  protected WordbankRepository wordbankRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Map<Object, Object> getWordbanks() {
    return wordbankRepository.findAllWordbanks();
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public Wordbank getWordbank(@PathVariable String name) throws WordbankNotFoundException {
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    if (wordbank == null) throw new WordbankNotFoundException();
    return wordbank;
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.POST)
  public Wordbank createWordbank(@PathVariable String name, @RequestBody Set<String> words) throws DuplicateWordbankException {
    if (wordbankExists(name)) throw new DuplicateWordbankException();

    Wordbank listone = new Wordbank(name, words);
    wordbankRepository.saveWordbank(listone);
    return listone;
  }

  @RequestMapping(value = "/{name}/addWord", method = RequestMethod.PUT)
  public Wordbank addToWordbank(@PathVariable String name, @RequestBody Set<String> wordsToAdd) throws WordbankNotFoundException {
    // adds words to list
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    if (wordbank == null) throw new WordbankNotFoundException();

    Set<String> words = wordbank.getWords();
    words.addAll(wordsToAdd);
    wordbank.setWords(words);
    wordbankRepository.updateWordbank(wordbank);
    return wordbank;
  }

  @RequestMapping(value = "/{name}/removeWord", method = RequestMethod.DELETE)
  public Wordbank removeFromWordbank(@PathVariable String name, @RequestBody Set<String> wordsToDelete) throws WordbankNotFoundException {
    // remove words from list
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    if (wordbank == null) throw new WordbankNotFoundException();

    Set<String> words = wordbank.getWords();
    words.removeAll(wordsToDelete);
    wordbank.setWords(words);
    wordbankRepository.updateWordbank(wordbank);
    return wordbank;
  }

  @RequestMapping(value = "/{name}/", method = RequestMethod.PUT)
  public Wordbank updateWordbank(@PathVariable String name, @RequestBody Set<String> words) throws WordbankNotFoundException {
    // change old list to new list
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    if (wordbank == null) throw new WordbankNotFoundException();
    wordbank.setWords(words);
    wordbankRepository.updateWordbank(wordbank);
    return wordbank;
  }

  @RequestMapping(value = "/{name}/", method = RequestMethod.DELETE)
  public void deleteWordbank(@PathVariable String name) throws WordbankNotFoundException {
    if (!wordbankExists(name)) throw new WordbankNotFoundException();
    wordbankRepository.deleteWordbank(name);
  }

  protected Boolean wordbankExists(String name) {
    Wordbank wordbank = wordbankRepository.findWordbank(name);
    return wordbank != null;
  }

}
