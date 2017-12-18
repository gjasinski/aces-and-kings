package pl.edu.agh.to2.acesandkings.common.model;


public interface GamePlayer {

    public Board undo();

    public Board redo();

    public void makeMove(Board board);

}
