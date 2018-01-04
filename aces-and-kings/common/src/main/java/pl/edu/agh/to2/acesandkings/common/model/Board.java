package pl.edu.agh.to2.acesandkings.common.model;

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
}
