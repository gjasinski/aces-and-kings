package pl.edu.agh.to2.acesandkings.game;

import com.google.inject.AbstractModule;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

public class GameModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(CardStackRepositoryImpl.class).toInstance(new CardStackRepositoryImpl());
    }
}
