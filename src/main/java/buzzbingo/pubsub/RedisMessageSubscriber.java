package buzzbingo.pubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {
  public static List<String> messageList = new ArrayList<String>();

  public void onMessage(Message message, byte[] pattern) {
    /*
    Note that there is a second parameter called pattern,
    which we have not used in this example. The Spring Data Redis
    documentation states that this parameter represents the,
    “pattern matching the channel (if specified)”,
    but that it can be null.
     */
    messageList.add(message.toString());
    System.out.println("Message received: " + message.toString());
  }
}
