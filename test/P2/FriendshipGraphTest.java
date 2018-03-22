package P2;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.*;

/**
 * FriendshipGraph Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>21 3, 2018</pre>
 */
public class FriendshipGraphTest {

    private FriendshipGraph graphTest = new FriendshipGraph(), graphTest1 = new FriendshipGraph();
    private Person[] persons = new Person[1000], persons1 = new Person[20];

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addVertex(Person newPerson)
     */
    @Test
    public void testAddVertex() throws Exception {
        /*
         * test case one
         * test strategy:
         * this test case add 1000 persons to the graph,
         * and add 1000 edges ,the edges are regular
         * try to get distance between every two persons
         */
        for (int i = 0; i < 1000; i++) {
            persons[i] = new Person(String.valueOf(i));
            graphTest.addVertex(persons[i]);
        }
         /*
         * end test case one
         */

        /*
         * test case two
         * test strategy
         * read data from file to avoid add them in my code
         * person : persons have different name, persons have the same name
         * getDistance: distance=-1, distance=0 ,distance >= 1
         */
        for (int i = 0; i < 14; i++) {
            persons1[i] = new Person(String.valueOf((int) 'a' + i));
            graphTest1.addVertex(persons1[i]);
        }
    }

    /**
     * Method: addEdge(Person personA, Person personB)
     */
    @Test
    public void testAddEdge() throws Exception {
        testAddVertex();
        /*
         * test case one
         * test strategy:
         * this test case add 1000 persons to the graph,
         * and add 1000 edges ,the edges are regular
         * try to get distance between every two persons
         */
        for (int i = 0; i < 999; i++) {
            graphTest.addEdge(persons[i], persons[i + 1]);
            graphTest.addEdge(persons[i + 1], persons[i]);
        }
        /*
         * end test case one
         */

        /*
         * test case two
         * test strategy
         * read data from file to avoid add them in my code
         * person : persons have different name, persons have the same name
         * getDistance: distance=-1, distance=0 ,distance >= 1
         */
        BufferedReader content = new BufferedReader(new FileReader("./test/P2/edge.txt"));
        String line;
        String[] integer;
        while ((line = content.readLine()) != null) {
            integer = line.split(" ");
            graphTest1.addEdge(persons1[Integer.parseInt(integer[0])], persons1[Integer.parseInt(integer[1])]);
        }
        content.close();
        /*
         * end test case two
         */
    }

    /**
     * Method: getDistance(Person personA, Person personB)
     */
    @Test
    public void testGetDistance() throws Exception {
        testAddEdge();
        /*
         * test case one
         */
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(i, graphTest.getDistance(persons[0], persons[i]));
        }
        /*
         * end test case one
         */

        /*
         * test case two
         */
        Assert.assertEquals(2, graphTest1.getDistance(persons1[0], persons1[5]));
        Assert.assertEquals(3, graphTest1.getDistance(persons1[0], persons1[6]));
        Assert.assertEquals(2, graphTest1.getDistance(persons1[0], persons1[3]));
        Assert.assertEquals(-1, graphTest1.getDistance(persons1[0], persons1[9]));
        Assert.assertEquals(2, graphTest1.getDistance(persons1[9], persons1[13]));
        Assert.assertEquals(2, graphTest1.getDistance(persons1[2], persons1[7]));
        /*
         * end test case two
         */
    }
} 
