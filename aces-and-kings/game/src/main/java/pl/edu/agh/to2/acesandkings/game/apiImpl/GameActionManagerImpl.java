package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.api.GameActionManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import javax.inject.Inject;

public class GameActionManagerImpl implements GameActionManager {
    private CardStackRepository cardStackRepository;
    private GamePlayer gamePlayer;

    @Inject
    public GameActionManagerImpl(CardStackRepository cardStackRepository, GamePlayer gamePlayer) {
        this.cardStackRepository = cardStackRepository;
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void undo() {
        Board previousBoard = this.gamePlayer.undo();
        updateCardStackRepository(previousBoard);
    }

    @Override
    public void redo() {
        Board nextBoard = this.gamePlayer.redo();
        updateCardStackRepository(nextBoard);
    }

    private void updateCardStackRepository(Board previousBoard) {
        previousBoard.getStacks().forEach(stack -> {
            addCardsToStack(stack);
            setStackState(stack);
        });
    }

    private void addCardsToStack(CardStack stack) {
        this.cardStackRepository.setUpNewCardOrder(stack.getPosition(), stack.getStack());
    }

    private void setStackState(CardStack stack) {
        this.cardStackRepository.changeStackState(stack.getPosition(), stack.getState());
    }
}
