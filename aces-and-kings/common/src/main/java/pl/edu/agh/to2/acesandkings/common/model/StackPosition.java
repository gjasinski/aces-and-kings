package pl.edu.agh.to2.acesandkings.common.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StackPosition {
    ACE(Rank.ACE),
    TWO(Rank.TWO),
    THREE(Rank.THREE),
    FOUR(Rank.FOUR),
    FIVE(Rank.FIVE),
    SIX(Rank.SIX),
    SEVEN(Rank.SEVEN),
    EIGHT(Rank.EIGHT),
    NINE(Rank.NINE),
    TEN(Rank.TEN),
    JACK(Rank.JACK),
    QUEEN(Rank.QUEEN),
    HEART_KING,
    HEART_ACE,
    DIAMONDS_KING,
    DIAMONDS_ACE,
    CLUBS_KING,
    CLUBS_ACE,
    SPADES_KING,
    SPADES_ACE,
    EXTRA_STACK;

    private Rank rank;
    private boolean middleStackPosition;

    StackPosition(Rank rank){
        this.rank = rank;
        this.middleStackPosition = true;
    }

    StackPosition(){
        this.middleStackPosition = false;
    }

    public boolean isPositionKing() {
        return this.equals(CLUBS_KING) || this.equals(DIAMONDS_KING) ||
                this.equals(HEART_KING) || this.equals(SPADES_KING);
    }

    public boolean isPositionAce() {
        return this.equals(CLUBS_ACE) || this.equals(DIAMONDS_ACE) ||
                this.equals(HEART_ACE) || this.equals(SPADES_ACE);
    }

    public boolean isMiddleStackPosition() {
        return this.middleStackPosition;
    }

    public static List<StackPosition> getMiddlePositions(){
        return Arrays.stream(StackPosition.values())
                .filter(StackPosition::isMiddleStackPosition)
                .collect(Collectors.toList());
    }

    public Rank getRank() {
        return rank;
    }
}
