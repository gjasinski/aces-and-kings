package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.player.DB.GraphDatabaseConnection;

import java.util.Queue;


public class PersistenceManager {
    GraphDatabaseConnection graphDatabaseConnection;
    private Queue<Change> cachedChanges;

    public void saveCachedValuesToDatabase(){
    }

    public boolean hasNoCachedValues(){
        return true;
    }

    Change takePreviousBoardStateFromDatabase(){
        return null;
//        return new Change(0, 0, 0);
    }

    Change takeNextBoardStateFromDatabase(){
        return null;
//        return new Change(0, 0, 0);
    }

    void saveMove(Change change){
    }
}
