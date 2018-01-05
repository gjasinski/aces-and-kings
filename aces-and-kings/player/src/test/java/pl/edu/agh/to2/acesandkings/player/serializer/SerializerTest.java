package pl.edu.agh.to2.acesandkings.player.serializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.Board;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.player.DB.Serializer;
import pl.edu.agh.to2.acesandkings.player.Player.CardStackModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SerializerTest {
    @Test
    public void reflection() {
        Card c = new Card(Suit.CLUBS, Rank.ACE);
        LinkedList<Card> cards = new LinkedList<>();
        cards.add(c);
        CardStackModel s = new CardStackModel(cards, State.INACTIVE, StackPosition.FIVE);
        LinkedList<CardStack> stacks = new LinkedList<>();
        stacks.add(s);
        Board b = new Board(stacks, 0);

        Map<String, Object> r = ((List<Map<String, Object>>)Serializer.serialize(b).get("stacks")).get(0);
        List<Map<String, Object>> rList = new LinkedList<>();
        for(Map<String, Object> rc: (List<Map<String, Object>>) r.get("cards")){
            Map<String, Object> row = new HashMap<>();
            Map<String, Object> stack = new HashMap<>();
            row.put("card", rc);
            stack.put("position", r.get("position"));
            stack.put("state", r.get("state"));
            row.put("stack", stack);
            rList.add(row);
        }

        Assert.assertTrue(b.compare(Serializer.deserializeBoard(rList, b.getId())));
    }
}
