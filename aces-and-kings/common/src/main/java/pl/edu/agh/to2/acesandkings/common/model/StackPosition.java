package pl.edu.agh.to2.acesandkings.common.model;

public enum StackPosition {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    HEART_KING,
    HEART_ACE,
    DIAMONDS_KING,
    DIAMONDS_ACE,
    CLUBS_KING,
    CLUBS_ACE,
    SPADES_KING,
    SPADES_ACE,
    EXTRA_STACK;

    public boolean isPositionKing() {
        return this.equals(CLUBS_KING) || this.equals(DIAMONDS_KING) ||
                this.equals(HEART_KING) || this.equals(SPADES_KING);
    }

    public boolean isPositionAce() {
        return this.equals(CLUBS_ACE) || this.equals(DIAMONDS_ACE) ||
                this.equals(HEART_ACE) || this.equals(SPADES_ACE);
    }

    public boolean isPositionMiddle() {
        return this.ordinal() < 12;
    }
}
