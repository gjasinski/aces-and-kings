package pl.edu.agh.to2.acesandkings.common.model;


public interface GamePlayer {

    Board startGame();

    Board undo();

    Board redo();

    void makeMove(Board board);

    Board restoreGame(int id, int step); // step=-1 for all

}
