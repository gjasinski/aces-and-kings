package pl.edu.agh.to2.acesandkings.app;

import pl.edu.agh.to2.acesandkings.game.MessageService;
import pl.edu.agh.to2.acesandkings.player.MessageRepository;
import pl.edu.agh.to2.acesandkings.vis.MessagePrinter;

/**
 * Created by Paweł Grochola on 29.11.2017.
 */
public class Application {
    public static void main(String[] args) {
        final MessageRepository messageRepository = new MessageRepository();
        final MessageService messageService = new MessageService(messageRepository);
        final MessagePrinter messagePrinter = new MessagePrinter(messageService);

        messagePrinter.showMessage();
    }
}
