package pl.edu.agh.to2.acesandkings.game;

import pl.edu.agh.to2.acesandkings.player.MessageRepository;

/**
 * Created by Paweł Grochola on 29.11.2017.
 */
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String getMessage() {
        return messageRepository.getMessage();
    }
}
