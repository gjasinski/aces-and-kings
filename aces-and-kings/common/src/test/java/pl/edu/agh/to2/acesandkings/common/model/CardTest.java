package pl.edu.agh.to2.acesandkings.common.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardTest {
    private Card card;

    @Before
    public void setUp() throws Exception {
        card = new Card(Suit.CLUBS, Rank.EIGHT);
    }

    @Test //[1]
    public void createCardTest() {
        Assert.assertTrue(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.CLUBS);
    }

    @Test //[2]
    public void equalsCardTest() {
        Card card2 = new Card(Suit.CLUBS, Rank.EIGHT);
        Assert.assertTrue(card.equals(card2));
    }
}