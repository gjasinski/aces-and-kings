package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.GamePlayer;

public class GamePlayerImpl implements GamePlayer{
    private Board lastBoard;
    private Board currentBoard;
    private Board nextBoard;
    private Serializer serializer;
    private PersistenceManager persistenceManager;

    public GamePlayer(Serializer serializer, PersistenceManager persistenceManager){
        this.persistenceManager = persistenceManager;
        this.serializer = serializer;
    }

    public Board undo(){
        nextBoard = currentBoard;
        currentBoard = lastBoard;
        return currentBoard;
        // lastBoard = ...
    }

    public Board redo(){
        lastBoard = currentBoard;
        currentBoard = nextBoard;
        // nextBoard = ...
        return currentBoard;
    }

    public void makeMove(Board board){
        lastBoard = currentBoard;
        currentBoard = board;
        // add to database ...
    }

    public Board getBoard(){
        return currentBoard;
    }
}
