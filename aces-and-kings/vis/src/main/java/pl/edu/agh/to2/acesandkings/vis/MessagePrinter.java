package pl.edu.agh.to2.acesandkings.vis;

import pl.edu.agh.to2.acesandkings.game.MessageService;

/**
 * Created by Paweł Grochola on 29.11.2017.
 */
public class MessagePrinter {
    private final MessageService messageService;

    public MessagePrinter(final MessageService messageService) {
        this.messageService = messageService;
    }

    public void showMessage() {
        System.out.println(messageService.getMessage());
    }
}
