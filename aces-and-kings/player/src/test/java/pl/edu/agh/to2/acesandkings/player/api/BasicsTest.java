package pl.edu.agh.to2.acesandkings.player.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import pl.edu.agh.to2.acesandkings.common.model.Board;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.player.API.Queries;
import pl.edu.agh.to2.acesandkings.player.Player.CardStackModel;
import pl.edu.agh.to2.acesandkings.player.Player.Change;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BasicsTest {
    private LinkedList<CardStack> stacks;
    private Change change1;
    private Change change2;
    private Card c1;
    private Card c2;
    private Card c3;
    private Card c4;
    private CardStack s1;
    private CardStack s2;

    @Before
    public void setUp() throws Exception {
        LinkedList<Card> cards = new LinkedList<>();
        c1 = new Card(Suit.CLUBS, Rank.ACE);
        c2 = new Card(Suit.CLUBS, Rank.KING);
        cards.add(c1);
        cards.add(c2);
        s1 = new CardStackModel(cards, State.INACTIVE, StackPosition.ACE);
        stacks = new LinkedList<>();
        stacks.add(s1);
        LinkedList<Card> cards2 = new LinkedList<>();
        c3 = new Card(Suit.DIAMONDS, Rank.TEN);
        c4 = new Card(Suit.DIAMONDS, Rank.THREE);
        cards2.add(c3);
        cards2.add(c4);
        s2 = new CardStackModel(cards2, State.INACTIVE, StackPosition.EIGHT);
        stacks.add(s2);
        change1 = new Change(StackPosition.ACE, StackPosition.EIGHT, c1, 1);
        change2 = new Change(StackPosition.EIGHT, StackPosition.ACE, c3, 2);
    }
    
    @Test
    public void insertAndGetTest(){
        Board b = Queries.initializeGame(stacks);        
        Board fromDB = Queries.getBoard(b.getId(), -1);
        Assert.assertTrue(fromDB.compare(b));
    }

    @Test 
    public void insertChangesAndGetAllTest(){
        Board b = Queries.initializeGame(stacks);
        Queries.createChange(b.getId(), change1);
        Queries.createChange(b.getId(), change2);
        Board fromDB = Queries.getBoard(b.getId(), -1);

        LinkedList<Card> cards1 = new LinkedList<>();
        LinkedList<Card> cards2 = new LinkedList<>();
        LinkedList<CardStack> stacks = new LinkedList<>();
        cards1.add(c2);
        cards1.add(c3);
        cards2.add(c1);
        cards2.add(c4);
        stacks.add(new CardStackModel(cards1, s1.getState(), s1.getPosition()));
        stacks.add(new CardStackModel(cards2, s2.getState(), s2.getPosition()));
        Board b2 = new Board(stacks, b.getId());
        Assert.assertTrue(fromDB.compare(b2));
    }

    public void insertChangesAndGetNthTest(){
        Board b = Queries.initializeGame(stacks);
        Queries.createChange(b.getId(), change1);
        Queries.createChange(b.getId(), change2);
        Board fromDB = Queries.getBoard(b.getId(), 1);

        LinkedList<Card> cards1 = new LinkedList<>();
        LinkedList<Card> cards2 = new LinkedList<>();
        LinkedList<CardStack> stacks = new LinkedList<>();
        cards1.add(c2);
        cards2.add(c1);
        cards2.add(c3);
        cards2.add(c4);
        stacks.add(new CardStackModel(cards1, s1.getState(), s1.getPosition()));
        stacks.add(new CardStackModel(cards2, s2.getState(), s2.getPosition()));
        Board b2 = new Board(stacks, b.getId());
        Assert.assertTrue(fromDB.compare(b2));
    }

    public void noBoardTest(){
        Board b = Queries.initializeGame(stacks);
        Board b2 = Queries.getBoard(b.getId()+1);
        Assert.assertTrue(b2 == null);
    }

    public void countStepsTest(){
        Board b = Queries.initializeGame(stacks);
        Queries.createChange(b.getId(), change1);
        Queries.createChange(b.getId(), change2);
        Assert.assertEquals(2, Queries.countSteps(b.getId()));
    }
}
