package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.player.API.Change;
import pl.edu.agh.to2.acesandkings.player.DB.GraphDatabaseConnection;

/**
 * Copyright Miron Markowski 2017.
 */
public class PersistenceManager {
    GraphDatabaseConnection graphDatabaseConnection;

    void saveCachedValuesToDatabase(){
    }

    Change takePreviousBoardStateFromDatabase(){

        return new Change(0, 0, 0);
    }

    Change takeNextBoardStateFromDatabase(){
        return new Change(0, 0, 0);
    }

    void saveMove(Change change){
    }
}
