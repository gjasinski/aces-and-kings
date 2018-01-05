package pl.edu.agh.to2.acesandkings.player.Player;

import java.util.List;
import pl.edu.agh.to2.acesandkings.common.model.Board;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.GamePlayer;

import pl.edu.agh.to2.acesandkings.player.API.Queries;
import pl.edu.agh.to2.acesandkings.player.DB.Serializer;

public class GamePlayerImpl implements GamePlayer{
    private Board currentBoard;
    private Serializer serializer;
    private int step;

    public GamePlayerImpl(){
        this.serializer = new Serializer();
        step = 1;
    }

    public Board startGame(List<CardStack> stacks){
        return Queries.initializeGame(stacks);
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

    public Board restoreGame(List<CardStack> stacks){
        Board b = Queries.getBoard(0, -1);
        if(b == null){
            b = Queries.initializeGame();
        }
        this.step = Queries.countSteps(b.getId());
        return b;
    }

    @Override
    public Board restoreGame(int id, int step){
        this.step = step;
        return Queries.getBoard(id, step);
    }
}