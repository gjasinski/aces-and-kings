package pl.edu.agh.to2.acesandkings.common.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Board {
    private List<CardStack> stacks;
    private final int id;

    public Board(List<CardStack> stacks, int id){
        this.stacks = stacks;
        this.id = id;
    }

    public List<CardStack> getStacks() {
        return stacks;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Board:\n");
        for(CardStack stack: stacks){
            s.append("\t").append(stack.getPosition()).append(" -> ");
            for(Card c: stack.getStack()){
                s.append(c.getRank()).append(" ").append(c.getSuit()).append(", ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int getId(){
        return id;
    }

    public boolean compare(Board b){
        List<CardStack> stacks = this.stacks;
        HashMap<StackPosition, CardStack> otherStacks = new HashMap<>();
        for(CardStack s: b.stacks){
            otherStacks.put(s.getPosition(), s);
        }
        for(CardStack s: stacks){
            CardStack other = otherStacks.get(s.getPosition());
            if(other == null) {
                return false;
            }
            HashSet otherCards = new HashSet<>();
            for(Card c: other.getStack()){
                otherCards.add(c);
            }
            for(Card c: s.getStack()){
                if(!otherCards.contains(c)){
                    return false;
                }
                otherCards.remove(c);
            }
            if(otherCards.size() > 0){
                return false;
            }
        }
        return true;
    }
}
