package buzzbingo.controllers;

import buzzbingo.exceptions.DuplicateWordbankException;
import buzzbingo.exceptions.WordbankNotFoundException;
import buzzbingo.model.Wordbank;
import buzzbingo.pubsub.RedisMessagePublisher;
import buzzbingo.repositories.WordbankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(ApiBaseController.API_VERSION + "/wordbank")
public class WordbankController extends ApiBaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(WordbankController.class);

  @Autowired
  protected WordbankRepository wordbankRepository;


// -----------------------

  @Autowired
  RedisMessagePublisher redisMessagePublisher;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public void testpubsub() {
    LOGGER.info(">> sending message");
    String message = "Message " + UUID.randomUUID();
    LOGGER.info(">> message: " + message);
    redisMessagePublisher.publish(message);
    LOGGER.info(">> message sent");
//
//    boolean contains = RedisMessageSubscriber.messageList.get(0).contains(message);
//    LOGGER.info(">> contains?");
//    LOGGER.info(String.valueOf(contains));

  }

// -----------------------


  //TODO: Should this just return the names?
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Collection<Object> getWordbanks() {
    Map<Object, Object> allWordbanks = wordbankRepository.findAllWordbanks();
    return allWordbanks.values();
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
