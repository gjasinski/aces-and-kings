package pl.edu.agh.to2.acesandkings.player.DB;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.player.Player.CardStackModel;
import pl.edu.agh.to2.acesandkings.player.Player.Change;

import java.util.*;
import java.util.stream.Collectors;

public class Serializer {
    public Change serialize(Board nextBoard, Board lastBoard){
        return null;
    }


    public static Map<String, Object> serialize(Card c){
        HashMap<String, Object> m = new HashMap<>();
        m.put("rank", c.getRank().toString());
        m.put("suit", c.getSuit().toString());
        return m;
    }

    public static Map<String, Object> serialize(CardStack s){
        HashMap<String, Object> m = new HashMap<>();
        m.put("state", s.getState().toString());
        m.put("position", s.getPosition().toString());
        List<Map<String, Object>> cards = new LinkedList<>();
        s.getStack().forEach(c -> cards.add(serialize(c)));
        m.put("cards", cards);
        return m;
    }

    public static Map<String, Object> serialize(Board b){
        HashMap<String, Object> m = new HashMap<>();
        List<Map<String, Object>> stacks = new LinkedList<>();
        b.getStacks().forEach(s -> stacks.add(serialize(s))) ;
        m.put("stacks", stacks);
        return m;
    }

    public static Map<String, Object> serialize(Change c){
        HashMap<String, Object> m = new HashMap<>();
        m.put("step", Integer.toString(c.getStep()));
        m.put("previous", c.getPreviousStackPosition().toString());
        m.put("next", c.getNextStackPosition().toString());
        m.put("suit", c.getCard().getSuit().toString());
        m.put("rank", c.getCard().getRank().toString());
        return m;
    }

    public static Card deserializeCard(Map<String, Object> o){
        return new Card(
                Suit.valueOf((String) o.get("suit")),
                Rank.valueOf((String) o.get("rank"))
        );
    }

    public static CardStack deserializeStack(List<Map<String, Object>> queryResult){
        List<Card> cards = new LinkedList<>();
        queryResult.forEach(m -> cards.add(deserializeCard((Map<String, Object>) m.get("card"))));

        Map<String, Object> stack = (Map<String, Object>) queryResult.get(0).get("stack");
        State state = State.valueOf((String) stack.get("state"));
        StackPosition stackPosition = StackPosition.valueOf((String) stack.get("position"));
        return new CardStackModel(cards, state, stackPosition);
    }


    private static class CardlessStack {
        public StackPosition position;
        public State state;

        public CardlessStack(Map<String, Object> map){
            position = StackPosition.valueOf((String) map.get("position"));
            state = State.valueOf((String) map.get("state"));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CardlessStack that = (CardlessStack) o;
            return position == that.position &&
                    state == that.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, state);
        }
    }

    public static Board deserializeBoard(List<Map<String, Object>> queryResult, int id){
        Map<CardlessStack, List<Card>> stackMap = new HashMap<>();
        queryResult.forEach(m -> {
            CardlessStack cardlessStack = new CardlessStack((Map<String, Object>) m.get("stack"));
            Card card = Serializer.deserializeCard((Map<String, Object>) m.get("card"));
            if(stackMap.containsKey(cardlessStack)){
                stackMap.get(cardlessStack).add(card);
            } else {
                List<Card> list = new LinkedList<>();
                list.add(card);
                stackMap.put(cardlessStack, list);
            }
        });

        List<CardStack> stacks = stackMap.entrySet().stream().map(e ->
                new CardStackModel(e.getValue(), e.getKey().state, e.getKey().position)
        ).collect(Collectors.toList());

        return new Board(stacks, id);
    }

    public static List<Change> deserializeChanges(List<Map<String, Object>> changeQueryResult){
        if(changeQueryResult.get(0).get("change") == null){
            return new LinkedList<>();
        }
        return changeQueryResult.stream()
            .map(m -> {
                    Map<String, Object> c = (Map<String, Object>) m.get("change");
                    return new Change(
                        StackPosition.valueOf((String) c.get("previous")), 
                        StackPosition.valueOf((String) c.get("next")), 
                        new Card(
                            Suit.valueOf((String) c.get("suit")), 
                            Rank.valueOf((String) c.get("rank"))
                        ), 
                        Integer.parseInt((String)c.get("step"))
                    );
                }
            ).collect(Collectors.toList());
    }

    public static Board deserializeChangedBoard(Board board, List<Map<String, Object>> changeQueryResult, int id){
        List<Change> changes = deserializeChanges(changeQueryResult);

        changes.sort((c1, c2) -> {
            if(c1.getStep() == c2.getStep()) {
                return 0;
            }
            return c1.getStep() < c2.getStep() ? -1 : 1;
        });

        for(Change c: changes){
            board = c.applyTo(board);
        }

        return board;
    }
}
