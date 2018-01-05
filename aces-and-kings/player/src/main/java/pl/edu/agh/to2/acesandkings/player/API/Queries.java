package pl.edu.agh.to2.acesandkings.player.API;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.player.DB.GraphDatabaseConnection;
import pl.edu.agh.to2.acesandkings.player.Player.CardStackModel;
import pl.edu.agh.to2.acesandkings.player.Player.Change;
import pl.edu.agh.to2.acesandkings.player.DB.Serializer;

import java.util.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Copyright Miron Markowski 2017.
 */
public class Queries {
    public static void dropAll(){
        Session s = GraphDatabaseConnection.getSession();
        s.writeTransaction(tx -> tx.run("MATCH (n) DETACH DELETE n"));
    }

    private static List<CardStack> randomDistribution(){
        List<CardStack> res = new LinkedList<>();
        List<Card> cards = new LinkedList<>();
        Random r = new Random();
        Arrays.stream(Suit.values()).forEach(suit ->
            Arrays.stream(Rank.values()).forEach(rank ->
                cards.add(r.nextInt(cards.size() + 1), new Card(suit, rank))
            )
        );
        Arrays.stream(StackPosition.values()).forEach(position ->
            res.add(new CardStackModel(State.INACTIVE, position))
        );

        cards.forEach(card -> {
            CardStackModel stack = (CardStackModel) res.remove(res.size() - 1);
            stack.addCard(card);
            res.add(0, stack);
        });

        return res;
    }

    public static Board initializeGame(){
        return initializeGame(randomDistribution());
    }

    public static Board initializeGame(List<CardStack> distribution){
        Session s = GraphDatabaseConnection.getSession();

        StatementResult r = s.writeTransaction(tx -> tx.run(
           "MATCH (b:Board) RETURN max(b.id) as maxId", parameters()
        ));
        Object res = r.single().asMap().get("maxId");
        int lastId = res == null ? 0 : Integer.parseInt(res.toString()) + 1;

        Board board = new Board(distribution, lastId);

        s.writeTransaction(tx -> tx.run(
                "CREATE (b: Board {id: $id}) " +
                        " WITH (b)" +
                        " UNWIND $board.stacks as stack " +
                        " CREATE (b) -[:HAS]->(n:Stack {state: stack.state, position: stack.position})" +
                        " WITH (n), stack" +
                        " UNWIND stack.cards as card " +
                        " CREATE (n)-[:CONTAINS]->(m:Card {rank: card.rank, suit: card.suit})",
                parameters("board", Serializer.serialize(board), "id", lastId)
        ));

        return board;
    }

    public static void deleteNextChanges(int id, Change change){
        Session s = GraphDatabaseConnection.getSession();
        s.writeTransaction(tx -> tx.run(
           "MATCH (b:Board{id:$id})-[:PRECEDES*]->(c:Change) WHERE c.step >= $step DETACH DELETE c",
           parameters("id", id, "step", change.getStep()) 
        ));
    }

    public static void createChange(int id, Change change){
        Session s = GraphDatabaseConnection.getSession();

        deleteNextChanges(id, change);

        s.writeTransaction(tx -> tx.run(
                "MATCH (b:Board{id: $id}) WITH b " +
                " optional MATCH (b)-[:PRECEDES*]->(c) WHERE NOT (c)-[]->() " +
                " FOREACH(x in case when c is null then [b] else [c] end | " +
                " CREATE (x)-[:PRECEDES]->(d:Change " +
                " {previous: $change.previous, next: $change.next, rank: $change.rank, suit: $change.suit, step: $change.step}))",
                parameters("id", id, "change", Serializer.serialize(change))
        ));
    }

    // JUST A TEST
    public static Card getCard(Suit suit, Rank rank){
        Session s = GraphDatabaseConnection.getSession();
        return s.writeTransaction(tx ->
            {
                StatementResult r = tx.run(
                    "MATCH (c: Card {rank: $rank, suit: $suit}) RETURN c{.*}",
                    parameters("rank", rank.toString(), "suit", suit.toString()));
                return Serializer.deserializeCard((Map<String, Object>) r.single().asMap().get("c"));
            });
    }

    // JUST A TEST
    public static CardStack getStack(StackPosition position){
        Session s = GraphDatabaseConnection.getSession();
        return s.writeTransaction(tx -> {
            StatementResult r = tx.run(
                    "MATCH (stack:Stack {position: $position})-[:CONTAINS]->(card:Card) " +
                            "return stack, card ",
                    parameters("position", position.toString())
                );
            return Serializer.deserializeStack(r.list(Record::asMap));
            }
        );
    }

    public static List<Change> getChanges(int id, int step){
        Session s = GraphDatabaseConnection.getSession();
        String query;
        if(step < 0){
            query = "OPTIONAL MATCH (b:Board {id: $id})-[:PRECEDES*]->(change:Change) RETURN change{.*}";
        } else {
            query = "OPTIONAL MATCH (b:Board {id: $id})-[:PRECEDES*]->(change:Change) WHERE change.step <= $step RETURN change{.*}";
        }
        return s.writeTransaction(tx -> {
            StatementResult r = tx.run(
                query,
                parameters("id", id, "step", step)
            );
            return Serializer.deserializeChanges(r.list(Record::asMap));
        });
    }

    public static Board getBoard(int id){
        Session s = GraphDatabaseConnection.getSession();
        return s.writeTransaction(
                tx -> {
                    StatementResult r = tx.run(
                        " MATCH (b: Board {id: $id})-[:HAS]->(stack: Stack) " +
                        " WITH (stack) " +
                        " MATCH (stack) -[:CONTAINS]->(card:Card) " +
                        " RETURN stack{.*}, card{.*}",
                        parameters("id", id)
                    );

                    return Serializer.deserializeBoard(r.list(Record::asMap), id);
                }
        );
    }

    public static Board getBoard(int id, int step){
        Board board = getBoard(id);
        List<Change> changes = getChanges(id, step);

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
