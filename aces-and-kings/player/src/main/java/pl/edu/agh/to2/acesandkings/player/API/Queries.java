package pl.edu.agh.to2.acesandkings.player.API;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.player.DB.GraphDatabaseConnection;
import pl.edu.agh.to2.acesandkings.player.Player.Board;
import pl.edu.agh.to2.acesandkings.player.Player.CardStackModel;
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

    public static int initializeGame(){
        return initializeGame(randomDistribution());
    }

    public static int initializeGame(List<CardStack> distribution){
        Session s = GraphDatabaseConnection.getSession();

        StatementResult r = s.writeTransaction(tx -> tx.run(
           "MATCH (b:Board) RETURN max(b.id) as maxId", parameters()
        ));
        int lastId = (Integer) r.single().asMap().get("maxId");

        Board board = new Board(distribution, lastId + 1);

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

        return lastId + 1;
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

    public static Board getBoard(int id){
        Session s = GraphDatabaseConnection.getSession();
        return s.writeTransaction(
                tx -> {
                    StatementResult r = tx.run(
        "MATCH (b: Board {id: $id})-[:HAS]->(stack: Stack) " +
                        " WITH (stack) " +
                        " MATCH (stack) -[:CONTAINS]->(card:Card) " +
                " RETURN stack{.*}, card{.*}",
                            parameters("id", id)
                    );

                    return Serializer.deserializeBoard(r.list(Record::asMap), id);
                }
        );
    }
}
