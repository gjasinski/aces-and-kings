package pl.edu.agh.to2.acesandkings.game;

import com.google.inject.AbstractModule;
import pl.edu.agh.to2.acesandkings.common.model.GamePlayer;
import pl.edu.agh.to2.acesandkings.game.api.*;
import pl.edu.agh.to2.acesandkings.game.apiImpl.*;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;
import pl.edu.agh.to2.acesandkings.game.model.GameManagerImpl;
import pl.edu.agh.to2.acesandkings.player.Player.GamePlayerImpl;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardStackRepositoryImpl.class).toInstance(new CardStackRepositoryImpl());
        bind(ActiveCardsManipulator.class).to(ActiveCardsManipulatorImpl.class);
        bind(CardsInHandManipulator.class).to(CardsInHandManipulatorImpl.class);
        bind(CardsMovePossibilityGuard.class).to(CardsMovePossibilityGuardImpl.class);
        bind(CardStackManager.class).to(CardStackManagerImpl.class);
        bind(GameActionManager.class).to(GameActionManagerImpl.class);
        bind(GameManager.class).to(GameManagerImpl.class);
        bind(GamePlayer.class).toInstance(new GamePlayerImpl());
    }
}
