import org.junit.Assert;
import org.junit.Test;


public class CardTest {
    private Card card;

    @Test //[1]
    public void createCardTest() {
        card = new Card(Suit.CLUBS, Rank.EIGHT);
        Assert.assertTrue(card.getRank() == Rank.EIGHT && card.getSuit() == Suit.CLUBS);
    }

    @Test //[2]
    public void equalsCardTest() {
        card = new Card(Suit.CLUBS, Rank.EIGHT);
        Card card2 = new Card(Suit.CLUBS, Rank.EIGHT);

        Assert.assertTrue(card.equals(card2));
    }
}