package pl.edu.agh.to2.acesandkings.player.API;

/**
 * Copyright Miron Markowski 2017.
 */
public class Change {
    int previousStackID;
    int nextStackID;
    int cardId;

    public Change(int previousStackID, int nextStackID, int cardId){
        this.previousStackID = previousStackID;
        this.nextStackID = nextStackID;
        this.cardId = cardId;
    }
}
