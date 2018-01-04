package pl.edu.agh.to2.acesandkings.common.model;


public interface GamePlayer {

    Board startGame();

    Board undo();

    Board redo();

    void makeMove(Board board);

}
