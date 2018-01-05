package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.Board;
import pl.edu.agh.to2.acesandkings.common.model.GamePlayer;

import pl.edu.agh.to2.acesandkings.player.API.Queries;
import pl.edu.agh.to2.acesandkings.player.DB.Serializer;

public class GamePlayerImpl implements GamePlayer{
    private Board currentBoard;
    private Serializer serializer;
    private int step;

    public GamePlayerImpl(){
        this.serializer = new Serializer();
        step = 0;
    }

    public Board startGame() {
        currentBoard = Queries.initializeGame();
        return currentBoard;
    }

    public Board undo(){
        step--;
        currentBoard = Queries.getBoard(currentBoard.getId(), step);
        return currentBoard;
    }

    public Board redo(){
        step++;
        currentBoard = Queries.getBoard(currentBoard.getId(), step);
        return currentBoard;
    }

    
    public void makeMove(Board nextBoard){
        Change changeToMake = serializer.serialize(nextBoard, currentBoard);
        Queries.createChange(currentBoard.getId(), changeToMake);
        step++;
        currentBoard = Queries.getBoard(currentBoard.getId(), step);
    }

    @Override
    public Board restoreGame(int id, int step){
        this.step = step;
        return Queries.getBoard(id, step);
    }
}