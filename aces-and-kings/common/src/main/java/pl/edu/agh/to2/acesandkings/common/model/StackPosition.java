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
    HEART_KING(Rank.KING, Suit.HEARTS),
    HEART_ACE(Rank.ACE, Suit.HEARTS),
    DIAMONDS_KING(Rank.KING, Suit.DIAMONDS),
    DIAMONDS_ACE(Rank.ACE, Suit.DIAMONDS),
    CLUBS_KING(Rank.KING, Suit.CLUBS),
    CLUBS_ACE(Rank.ACE, Suit.CLUBS),
    SPADES_KING(Rank.KING, Suit.SPADES),
    SPADES_ACE(Rank.ACE, Suit.SPADES),
    EXTRA_STACK(),
    HAND_STACK();


    private Rank rank;
    private Suit suit;
    private boolean middleStackPosition = false;
    private boolean borderPosition = false;
    private static final int NUMBER_OF_MIDDLE_STACKS = 12;

    StackPosition(Rank rank){
        this.rank = rank;
        this.middleStackPosition = true;
    }

    StackPosition(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
        this.borderPosition = true;
    }

    StackPosition(){
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

    public boolean isBorderPosition() {
        return borderPosition;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public static List<StackPosition> getMiddlePositions(){
        return Arrays.stream(StackPosition.values())
                .filter(StackPosition::isMiddleStackPosition)
                .collect(Collectors.toList());
    }

    public static List<StackPosition> getBorderPositions(){
        return Arrays.stream(StackPosition.values())
                .filter(StackPosition::isBorderPosition)
                .collect(Collectors.toList());
    }

    public static int getNumberOfMiddleStacks() {
        return NUMBER_OF_MIDDLE_STACKS;
    }
}
