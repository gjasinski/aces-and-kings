package pl.edu.agh.to2.acesandkings.app.helloworld;

import pl.edu.agh.to2.acesandkings.game.helloworld.MessageService;
import pl.edu.agh.to2.acesandkings.player.helloworld.MessageRepository;
import pl.edu.agh.to2.acesandkings.vis.helloworld.MessagePrinter;

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
