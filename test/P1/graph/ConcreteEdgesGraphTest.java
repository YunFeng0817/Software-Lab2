/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    Graph<String> emptyInstance = emptyInstance();

    /*
     * Testing ConcreteEdgesGraph...
     */

    /* Testing strategy for ConcreteEdgesGraph.toString()
     * test the return thing of toString() is the wanted String or not
     */

    @Test
    public void testConcreteEdgesGraphToString() {
        emptyInstance().add("first");
        emptyInstance.set("first", "second", 10);
        emptyInstance.set("third", "fourth", 100);
        emptyInstance.set("third", "second", 20);

        assertEquals("the number of vertices is  4 ,the number of edges is 3", emptyInstance.toString());
    }

    
    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // vertex: two String type:the different ,the same,the null
    // weight:<0 >0 =0

    @Test
    public void testStructure() {
        // cover vertex :two same String
        // cover weight<0
        try {
            Edge<String> line1 = new Edge<>("first", "first", -1);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        // cover vertex:two different String
        // cover weight = 0
        try {
            Edge<String> line2 = new Edge<>("first", "second", 0);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        // cover weight>0
        Edge<String> line3 = new Edge<>("first", "third", 1);
        assertEquals("first", line3.getSource());
        assertEquals("third", line3.getTarget());
        assertEquals(1, line3.getWeight());
    }

}
