package pl.edu.agh.to2.acesandkings.player.DB;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.player.API.Queries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

public class GraphDatabaseConnection implements AutoCloseable {
    private static final String URI = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "password";
    private static Driver driver;
    private static Session session;
    private static GraphDatabaseConnection instance = new GraphDatabaseConnection();

    private GraphDatabaseConnection(){
        InitConnection(URI, USER, PASSWORD);
    }

    public static void abort() throws Exception{
        instance.close();
    }

    private void InitConnection(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        session = driver.session();
    }

    @Override
    public void close() throws Exception {
        session.close();
        driver.close();
    }

    public static Session getSession() {
        return session;
    }

    public static void main(String... args) throws Exception {
        Queries.dropAll();
        Queries.initializeGame();
        System.out.println(Queries.getBoard(0));
    }
}