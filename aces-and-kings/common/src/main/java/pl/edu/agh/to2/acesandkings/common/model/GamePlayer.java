package pl.edu.agh.to2.acesandkings.common.model;

import java.util.List;

public interface GamePlayer {
    Board startGame(List<CardStack> stacks);
    
    Board startGame();

    Board undo();

    Board redo();

    void makeMove(Board board);

    Board restoreGame(List<CardStack> stacks); // stacks to initialize board with, used if there is no saved board
    
    Board restoreGame(int id, int step); // step=-1 for all
}

