package pl.edu.agh.to2.acesandkings.player.DB;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;

public class GraphDatabaseConnection implements AutoCloseable {
    private static Driver driver;


    public static void InitConnection(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public static void printGreeting(final String message) {
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(tx -> {
                StatementResult result = tx.run("CREATE (a:Greeting) " +
                                "SET a.message = $message " +
                                "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", message));
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        }
    }

    private Object runQuery(final String query){
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                StatementResult r = tx.run(query);
                return r.single().get(0).asObject();
            });
        }
    }

    public static void main(String... args) throws Exception {
        InitConnection("bolt://localhost:7687", "neo4j", "password");
        //System.out.println(greeter.runQuery(""));
        printGreeting("ELO");
    }
}